package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.StockAdjustmentDTO;
import com.hilcoe.crms.entity.InventoryItem;
import com.hilcoe.crms.entity.StockMovement;
import com.hilcoe.crms.entity.Supplier;
import com.hilcoe.crms.repository.InventoryItemRepository;
import com.hilcoe.crms.repository.StockMovementRepository;
import com.hilcoe.crms.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    @Autowired
    private InventoryItemRepository inventoryItemRepository;
    @Autowired
    private StockMovementRepository stockMovementRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public InventoryItemResponseDTO addItem(InventoryItemDTO dto) {
        InventoryItem item = new InventoryItem();
        item.setName(dto.getName());
        item.setUnit(dto.getUnit());
        item.setQuantity(dto.getQuantity());
        item.setThreshold(dto.getThreshold());
        Supplier supplier = supplierRepository.findById(dto.getSupplierId()).orElseThrow();
        item.setSupplier(supplier);
        InventoryItem saved = inventoryItemRepository.save(item);
        return toResponseDTO(saved);
    }

    public InventoryItemResponseDTO updateItem(Long id, InventoryItemDTO dto) {
        InventoryItem item = inventoryItemRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.InventoryItemNotFoundException("Inventory item not found with id: " + id));
        item.setName(dto.getName());
        item.setUnit(dto.getUnit());
        item.setQuantity(dto.getQuantity());
        item.setThreshold(dto.getThreshold());
        Supplier supplier = supplierRepository.findById(dto.getSupplierId()).orElseThrow();
        item.setSupplier(supplier);
        InventoryItem updated = inventoryItemRepository.save(item);
        return toResponseDTO(updated);
    }

    public InventoryItemResponseDTO adjustStock(Long id, StockAdjustmentDTO dto) {
        InventoryItem item = inventoryItemRepository.findById(id).orElseThrow();
        BigDecimal newQuantity = item.getQuantity().add(dto.getQuantityChange());
        item.setQuantity(newQuantity);
        InventoryItem updated = inventoryItemRepository.save(item);
        StockMovement movement = new StockMovement();
        movement.setInventoryItem(item);
        movement.setQuantityChange(dto.getQuantityChange());
        movement.setReason(dto.getReason());
        stockMovementRepository.save(movement);
        return toResponseDTO(updated);
    }

    public void deleteItem(Long id) {
        inventoryItemRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.InventoryItemNotFoundException("Inventory item not found with id: " + id));
        inventoryItemRepository.deleteById(id);
    }

    public List<InventoryItemResponseDTO> generateReport() {
        return inventoryItemRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public PaginatedResponseDTO<InventoryItemResponseDTO> getInventoryPaginated(Pageable pageable) {
        Page<InventoryItem> page = inventoryItemRepository.findAll(pageable);
        List<InventoryItemResponseDTO> content = page.getContent().stream().map(this::toResponseDTO).collect(Collectors.toList());
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

    private InventoryItemResponseDTO toResponseDTO(InventoryItem item) {
        InventoryItemResponseDTO dto = new InventoryItemResponseDTO();
        dto.setInventoryItemId(item.getInventoryItemId());
        dto.setName(item.getName());
        dto.setUnit(item.getUnit());
        dto.setQuantity(item.getQuantity());
        dto.setThreshold(item.getThreshold());
        if (item.getSupplier() != null) {
            dto.setSupplierId(item.getSupplier().getSupplierId());
        }
        return dto;
    }
}