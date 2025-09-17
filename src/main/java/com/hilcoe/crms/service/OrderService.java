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
import com.hilcoe.crms.repository.OrderRepository;
import com.hilcoe.crms.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.dto.PaginatedResponseDTO;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderResponseDTO createOrder(OrderCreateDTO dto, Long userId) {
        Order order = new Order();
        order.setBranchId(dto.getBranchId());
        order.setTableId(dto.getTableId());
        order.setStaffId(userId);
        // Set initial status and totalAmount as needed
        Order savedOrder = orderRepository.save(order);
        for (OrderItemDTO itemDTO : dto.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrderId(savedOrder.getOrderId());
            item.setMenuItemId(itemDTO.getMenuItemId());
            item.setQuantity(itemDTO.getQuantity());
            // Set unitPrice as needed
            orderItemRepository.save(item);
        }
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

    public OrderResponseDTO updateStatus(Long id, StatusDTO dto) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));
        order.setStatus(Order.OrderStatus.valueOf(dto.getStatus()));
        Order updated = orderRepository.save(order);
        return toDTO(updated);
    }

    public void deleteOrder(Long id) {
        orderRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.OrderNotFoundException("Order not found with id: " + id));
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