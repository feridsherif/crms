package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.StockAdjustmentDTO;
import com.hilcoe.crms.dto.InventoryReportDTO;
import com.hilcoe.crms.entity.InventoryItem;
import com.hilcoe.crms.entity.StockMovement;
import com.hilcoe.crms.entity.Supplier;
import com.hilcoe.crms.repository.InventoryItemRepository;
import com.hilcoe.crms.repository.StockMovementRepository;
import com.hilcoe.crms.repository.SupplierRepository;
import com.hilcoe.crms.repository.UserRepository;
import com.hilcoe.crms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditLogService auditLogService;

    public InventoryItemResponseDTO addItem(InventoryItemDTO dto) {
        InventoryItem item = new InventoryItem();
        item.setName(dto.getName());
        item.setUnit(dto.getUnit());
        item.setQuantity(dto.getQuantity());
        item.setThreshold(dto.getThreshold());
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
            .orElseThrow(() -> new com.hilcoe.crms.exception.SupplierNotFoundException(dto.getSupplierId()));
        item.setSupplier(supplier);
        InventoryItem saved = inventoryItemRepository.save(item);
        auditLogService.log(getCurrentUserId(), "CREATE", "InventoryItem", saved.getInventoryItemId(), saved);
        return toResponseDTO(saved);
    }

    public InventoryItemResponseDTO updateItem(Long id, InventoryItemDTO dto) {
        InventoryItem item = inventoryItemRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.InventoryItemNotFoundException("Inventory item not found with id: " + id));
        InventoryItem before = new InventoryItem();
        before.setInventoryItemId(item.getInventoryItemId());
        before.setName(item.getName());
        before.setUnit(item.getUnit());
        before.setQuantity(item.getQuantity());
        before.setThreshold(item.getThreshold());
        before.setSupplier(item.getSupplier());
        item.setName(dto.getName());
        item.setUnit(dto.getUnit());
        item.setQuantity(dto.getQuantity());
        item.setThreshold(dto.getThreshold());
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
            .orElseThrow(() -> new com.hilcoe.crms.exception.SupplierNotFoundException(dto.getSupplierId()));
        item.setSupplier(supplier);
        InventoryItem updated = inventoryItemRepository.save(item);
        HashMap<String, Object> data = new HashMap<>();
        data.put("before", before);
        data.put("after", updated);
        auditLogService.log(getCurrentUserId(), "UPDATE", "InventoryItem", updated.getInventoryItemId(), data);
        return toResponseDTO(updated);
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found for username: " + username));
        return user.getUserId();
    }

    public InventoryItemResponseDTO adjustStock(Long id, StockAdjustmentDTO dto) {
        InventoryItem item = inventoryItemRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.InventoryItemNotFoundException("Inventory item not found with id: " + id));
        InventoryItem before = new InventoryItem();
        before.setInventoryItemId(item.getInventoryItemId());
        before.setName(item.getName());
        before.setUnit(item.getUnit());
        before.setQuantity(item.getQuantity());
        before.setThreshold(item.getThreshold());
        before.setSupplier(item.getSupplier());
        BigDecimal newQuantity = item.getQuantity().add(dto.getQuantityChange());
        item.setQuantity(newQuantity);
        InventoryItem updated = inventoryItemRepository.save(item);
        StockMovement movement = new StockMovement();
        movement.setInventoryItem(item);
        movement.setQuantityChange(dto.getQuantityChange());
        movement.setReason(dto.getReason());
        movement.setCreatedBy(getCurrentUserId());
        stockMovementRepository.save(movement);
        HashMap<String, Object> data = new HashMap<>();
        data.put("before", before);
        data.put("after", updated);
        auditLogService.log(getCurrentUserId(), "UPDATE", "InventoryItem", updated.getInventoryItemId(), data);
        return toResponseDTO(updated);
    }

    public void deleteItem(Long id) {
        InventoryItem item = inventoryItemRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.InventoryItemNotFoundException("Inventory item not found with id: " + id));
        auditLogService.log(getCurrentUserId(), "DELETE", "InventoryItem", item.getInventoryItemId(), item);
        inventoryItemRepository.deleteById(id);
    }

    public InventoryItemResponseDTO getInventoryItem(Long id) {
        InventoryItem item = inventoryItemRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.InventoryItemNotFoundException("Inventory item not found with id: " + id));
        return toResponseDTO(item);
    }

    public List<InventoryItemResponseDTO> getInventoryItems() {
        return inventoryItemRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public InventoryReportDTO generateReport() {
        List<InventoryItem> items = inventoryItemRepository.findAll();
        List<InventoryReportDTO.ItemReport> itemReports = new ArrayList<>();
        List<InventoryReportDTO.ItemReport> lowStockReports = new ArrayList<>();
        BigDecimal totalValue = BigDecimal.ZERO;
        for (InventoryItem item : items) {
            InventoryReportDTO.ItemReport report = new InventoryReportDTO.ItemReport();
            report.id = item.getInventoryItemId();
            report.name = item.getName();
            report.unit = item.getUnit();
            report.quantity = item.getQuantity();
            report.threshold = item.getThreshold();
            // Supplier info
            Supplier supplier = item.getSupplier();
            if (supplier != null) {
                InventoryReportDTO.SupplierInfo supplierInfo = new InventoryReportDTO.SupplierInfo();
                supplierInfo.id = supplier.getSupplierId();
                supplierInfo.name = supplier.getName();
                supplierInfo.contact = supplier.getContact();
                supplierInfo.phone = supplier.getPhone();
                supplierInfo.terms = supplier.getTerms();
                report.supplier = supplierInfo;
            }
            // Recent stock movements
            List<StockMovement> movements = stockMovementRepository.findTop5ByInventoryItem_InventoryItemIdOrderByMovementIdDesc(item.getInventoryItemId());
            List<InventoryReportDTO.StockMovementInfo> movementInfos = new ArrayList<>();
            for (StockMovement m : movements) {
                InventoryReportDTO.StockMovementInfo mi = new InventoryReportDTO.StockMovementInfo();
                // No date field in StockMovement, so use movementId as proxy (or add date if available)
                mi.date = String.valueOf(m.getMovementId());
                mi.quantityChange = m.getQuantityChange();
                mi.reason = m.getReason();
                movementInfos.add(mi);
            }
            report.recentMovements = movementInfos;
            // No price/cost, so itemValue is null
            report.itemValue = null;
            itemReports.add(report);
            if (item.getQuantity().compareTo(item.getThreshold()) < 0) {
                lowStockReports.add(report);
            }
        }
        InventoryReportDTO reportDTO = new InventoryReportDTO();
        reportDTO.items = itemReports;
        reportDTO.lowStockItems = lowStockReports;
        reportDTO.totalInventoryValue = totalValue;
        return reportDTO;
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