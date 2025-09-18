package com.hilcoe.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import com.hilcoe.crms.dto.InventoryReportDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.StockAdjustmentDTO;
import com.hilcoe.crms.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

	@PostMapping
	@PreAuthorize("hasAuthority('INVENTORY_CREATE')")
	public ResponseEntity<Object> addItem(@Valid @RequestBody InventoryItemDTO dto) {
		InventoryItemResponseDTO response = inventoryService.addItem(dto);
		return ResponseEntity.status(201).body(ApiResponse.success("Item added", response));
	}

	@PatchMapping("/{id}/adjust")
	@PreAuthorize("hasAuthority('INVENTORY_UPDATE')")
	public ResponseEntity<Object> adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustmentDTO dto) {
		InventoryItemResponseDTO response = inventoryService.adjustStock(id, dto);
		return ResponseEntity.ok(ApiResponse.success("Stock adjusted", response));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('INVENTORY_DELETE')")
	public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
		inventoryService.deleteItem(id);
		return ResponseEntity.ok(ApiResponse.success("Inventory item deleted", null));
	}

	@GetMapping("/report")
	@PreAuthorize("hasAuthority('INVENTORY_READ')")
	public ResponseEntity<Object> generateReport() {
		InventoryReportDTO report = inventoryService.generateReport();
		return ResponseEntity.ok(ApiResponse.success("Report generated", report));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getInventoryItem(@PathVariable Long id) {
		InventoryItemResponseDTO item = inventoryService.getInventoryItem(id);
		return ResponseEntity.ok(ApiResponse.success("Inventory item fetched", item));
	}

	@GetMapping
	public ResponseEntity<Object> getInventoryItems() {
		List<InventoryItemResponseDTO> items = inventoryService.getInventoryItems();
		return ResponseEntity.ok(ApiResponse.success("Inventory items fetched", items));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('INVENTORY_READ')")
	public ResponseEntity<Object> getInventoryPaginated(@RequestParam(required = false) String name,
			@RequestParam(required = false) Long supplierId, @RequestParam(required = false) String unit,
			@RequestParam(required = false) java.math.BigDecimal minQuantity,
			@RequestParam(required = false) java.math.BigDecimal maxQuantity,
			@RequestParam(required = false) java.math.BigDecimal minThreshold,
			@RequestParam(required = false) java.math.BigDecimal maxThreshold,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "inventoryItemId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<InventoryItemResponseDTO> items = inventoryService.getInventoryPaginatedAdvanced(name,
				supplierId, unit, minQuantity, maxQuantity, minThreshold, maxThreshold, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Inventory fetched", items));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('INVENTORY_UPDATE')")
	public ResponseEntity<Object> updateItem(@PathVariable Long id, @Valid @RequestBody InventoryItemDTO dto) {
		InventoryItemResponseDTO response = inventoryService.updateItem(id, dto);
		return ResponseEntity.ok(ApiResponse.success("Item updated", response));
	}
}