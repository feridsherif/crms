package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import com.hilcoe.crms.dto.StockAdjustmentDTO;
import com.hilcoe.crms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.InventoryReportDTO;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Object> addItem(@Valid @RequestBody InventoryItemDTO dto) {
        InventoryItemResponseDTO response = inventoryService.addItem(dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Item added", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable Long id, @Valid @RequestBody InventoryItemDTO dto) {
        InventoryItemResponseDTO response = inventoryService.updateItem(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Item updated", response));
    }

    @PatchMapping("/{id}/adjust")
    public ResponseEntity<Object> adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustmentDTO dto) {
        InventoryItemResponseDTO response = inventoryService.adjustStock(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Stock adjusted", response));
    }

    @GetMapping("/report")
    public ResponseEntity<Object> generateReport() {
        InventoryReportDTO report = inventoryService.generateReport();
        return ResponseEntity.ok(new ApiResponse<>("success", "Report generated", report));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Inventory item deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getInventoryPaginated(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<InventoryItemResponseDTO> items = inventoryService.getInventoryPaginated(PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponse<>("success", "Inventory fetched", items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInventoryItem(@PathVariable Long id) {
        InventoryItemResponseDTO item = inventoryService.getInventoryItem(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Inventory item fetched", item));
    }

    @GetMapping
    public ResponseEntity<Object> getInventoryItems() {
        List<InventoryItemResponseDTO> items = inventoryService.getInventoryItems();
        return ResponseEntity.ok(new ApiResponse<>("success", "Inventory items fetched", items));
    }
}