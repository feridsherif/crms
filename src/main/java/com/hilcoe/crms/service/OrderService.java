package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.OrderCreateDTO;
import com.hilcoe.crms.dto.OrderItemDTO;
import com.hilcoe.crms.dto.OrderResponseDTO;
import com.hilcoe.crms.dto.OrderFullResponseDTO;
import com.hilcoe.crms.dto.OrderItemResponseDTO;
import com.hilcoe.crms.dto.StatusDTO;
import com.hilcoe.crms.entity.Order;
import com.hilcoe.crms.entity.OrderItem;
import com.hilcoe.crms.exception.OrderNotFoundException;
import com.hilcoe.crms.exception.OrderStatusUpdateNotAllowedException;
import com.hilcoe.crms.exception.OrderDeleteNotAllowedException;
import com.hilcoe.crms.repository.OrderRepository;
import com.hilcoe.crms.repository.OrderItemRepository;
import com.hilcoe.crms.repository.MenuItemRepository;
import com.hilcoe.crms.entity.MenuItem;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.dto.PaginatedResponseDTO;

import java.util.List;
import com.hilcoe.crms.service.AuditLogService;
import java.util.HashMap;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private AuditLogService auditLogService;

    public OrderResponseDTO createOrder(OrderCreateDTO dto, Long userId) {
        Order order = new Order();
        order.setBranchId(dto.getBranchId());
        order.setTableId(dto.getTableId());
        order.setStaffId(userId);
        order.setStatus(Order.OrderStatus.CREATED);
        BigDecimal totalAmount = BigDecimal.ZERO;
        java.util.List<OrderItem> itemsToSave = new java.util.ArrayList<>();
        for (OrderItemDTO itemDTO : dto.getItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemDTO.getMenuItemId())
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found: " + itemDTO.getMenuItemId()));
            BigDecimal itemTotal = menuItem.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
            OrderItem item = new OrderItem();
            // orderId will be set after order is saved
            item.setMenuItemId(itemDTO.getMenuItemId());
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(menuItem.getPrice());
            itemsToSave.add(item);
        }
        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);
        for (OrderItem item : itemsToSave) {
            item.setOrderId(savedOrder.getOrderId());
            orderItemRepository.save(item);
        }
        auditLogService.log(userId, "CREATE", "Order", savedOrder.getOrderId(), savedOrder);
        return toDTO(savedOrder);
    }

    public OrderFullResponseDTO getOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));
        // Fetch order items
        java.util.List<OrderItem> items = orderItemRepository.findByOrder_OrderId(order.getOrderId());
        java.util.List<OrderItemResponseDTO> itemDTOs = items.stream().map(this::toOrderItemResponseDTO).toList();
        return toFullResponseDTO(order, itemDTOs);
    }

    public OrderResponseDTO updateStatus(Long id, StatusDTO dto, Long userId) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));
        Order before = new Order();
        before.setOrderId(order.getOrderId());
        before.setBranchId(order.getBranchId());
        before.setTableId(order.getTableId());
        before.setStaffId(order.getStaffId());
        before.setStatus(order.getStatus());
        before.setTotalAmount(order.getTotalAmount());
        Order.OrderStatus currentStatus = order.getStatus();
        Order.OrderStatus newStatus = Order.OrderStatus.valueOf(dto.getStatus());
        // Business rules
        if (newStatus == Order.OrderStatus.CANCELLED) {
            if (currentStatus != Order.OrderStatus.CREATED) {
                throw new OrderStatusUpdateNotAllowedException("Order can only be canceled if status is CREATED.");
            }
        } else if (newStatus == Order.OrderStatus.IN_PROGRESS) {
            if (currentStatus != Order.OrderStatus.CREATED) {
                throw new OrderStatusUpdateNotAllowedException("Order can only be set to IN_PROGRESS if status is CREATED.");
            }
        } else if (newStatus == Order.OrderStatus.COMPLETED) {
            if (currentStatus != Order.OrderStatus.IN_PROGRESS) {
                throw new OrderStatusUpdateNotAllowedException("Order can only be set to COMPLETED if status is IN_PROGRESS.");
            }
        } else {
            throw new OrderStatusUpdateNotAllowedException("Invalid status transition.");
        }
        order.setStatus(newStatus);
        Order updated = orderRepository.save(order);
        HashMap<String, Object> data = new HashMap<>();
        data.put("before", before);
        data.put("after", updated);
        auditLogService.log(userId, "UPDATE", "Order", updated.getOrderId(), data);
        return toDTO(updated);
    }

    public void deleteOrder(Long id, Long userId) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.OrderNotFoundException("Order not found with id: " + id));
        Order.OrderStatus status = order.getStatus();
        if (status != Order.OrderStatus.CANCELLED && status != Order.OrderStatus.COMPLETED) {
            throw new OrderDeleteNotAllowedException("Order can only be deleted if status is CANCELED or COMPLETED.");
        }
        auditLogService.log(userId, "DELETE", "Order", order.getOrderId(), order);
        orderRepository.deleteById(id);
    }

    public PaginatedResponseDTO<OrderFullResponseDTO> getOrdersPaginated(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        List<OrderFullResponseDTO> content = page.getContent().stream().map(order -> {
            java.util.List<OrderItem> items = orderItemRepository.findByOrder_OrderId(order.getOrderId());
            java.util.List<OrderItemResponseDTO> itemDTOs = items.stream().map(this::toOrderItemResponseDTO).toList();
            return toFullResponseDTO(order, itemDTOs);
        }).toList();
        int pageNum = page.getNumber();
        int pageSize = page.getSize();
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();
        String sort = pageable.getSort().toString();
        Object filter = null; // No filter logic for now
        long firstElementIndex = pageNum * pageSize + 1;
        long lastElementIndex = firstElementIndex + content.size() - 1;
        String nextPageUrl = null; // TODO: Generate if needed
        String previousPageUrl = null; // TODO: Generate if needed
        return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages,
            hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

    private OrderResponseDTO toDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getOrderId());
        dto.setStatus(order.getStatus().name());
        return dto;
    }

    private OrderFullResponseDTO toFullResponseDTO(Order order, java.util.List<OrderItemResponseDTO> items) {
        return new OrderFullResponseDTO(
            order.getOrderId(),
            order.getBranchId(),
            order.getTableId(),
            order.getStaffId(),
            order.getStatus().name(),
            order.getTotalAmount(),
            items
        );
    }

    private OrderItemResponseDTO toOrderItemResponseDTO(OrderItem item) {
        return new OrderItemResponseDTO(
            item.getOrderItemId(),
            item.getMenuItem().getMenuItemId(),
            item.getQuantity(),
            item.getUnitPrice()
        );
    }
}