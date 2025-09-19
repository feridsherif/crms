# InventoryController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `InventoryController` class, which manages inventory items, stock adjustments, reports, and paginated retrieval.

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and InventoryService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/inventory")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the InventoryService.

---

## Endpoint: Add Inventory Item

```java
@PostMapping
@PreAuthorize("hasAuthority('INVENTORY_CREATE')")
public ResponseEntity<Object> addItem(@Valid @RequestBody InventoryItemDTO dto) {
    InventoryItemResponseDTO response = inventoryService.addItem(dto);
    return ResponseEntity.status(201).body(ApiResponse.success("Item added", response));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/inventory`.
- Requires `INVENTORY_CREATE` authority.
- Adds a new inventory item.

---

## Endpoint: Adjust Stock

```java
@PatchMapping("/{id}/adjust")
@PreAuthorize("hasAuthority('INVENTORY_UPDATE')")
public ResponseEntity<Object> adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustmentDTO dto) {
    InventoryItemResponseDTO response = inventoryService.adjustStock(id, dto);
    return ResponseEntity.ok(ApiResponse.success("Stock adjusted", response));
}
```
- `@PatchMapping("/{id}/adjust")`: PATCH `/api/v1/inventory/{id}/adjust`.
- Requires `INVENTORY_UPDATE` authority.
- Adjusts the stock for an inventory item.

---

## Endpoint: Delete Inventory Item

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('INVENTORY_DELETE')")
public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
    inventoryService.deleteItem(id);
    return ResponseEntity.ok(ApiResponse.success("Inventory item deleted", null));
}
```
- `@DeleteMapping("/{id}")`: DELETE `/api/v1/inventory/{id}`.
- Requires `INVENTORY_DELETE` authority.
- Deletes an inventory item by ID.

---

## Endpoint: Generate Inventory Report

```java
@GetMapping("/report")
@PreAuthorize("hasAuthority('INVENTORY_READ')")
public ResponseEntity<Object> generateReport() {
    InventoryReportDTO report = inventoryService.generateReport();
    return ResponseEntity.ok(ApiResponse.success("Report generated", report));
}
```
- `@GetMapping("/report")`: GET `/api/v1/inventory/report`.
- Requires `INVENTORY_READ` authority.
- Generates and returns an inventory report.

---

## Endpoint: Get Inventory Item by ID

```java
@GetMapping("/{id}")
public ResponseEntity<Object> getInventoryItem(@PathVariable Long id) {
    InventoryItemResponseDTO item = inventoryService.getInventoryItem(id);
    return ResponseEntity.ok(ApiResponse.success("Inventory item fetched", item));
}
```
- `@GetMapping("/{id}")`: GET `/api/v1/inventory/{id}`.
- Returns a single inventory item by ID.

---

## Endpoint: Get All Inventory Items

```java
@GetMapping
public ResponseEntity<Object> getInventoryItems() {
    List<InventoryItemResponseDTO> items = inventoryService.getInventoryItems();
    return ResponseEntity.ok(ApiResponse.success("Inventory items fetched", items));
}
```
- `@GetMapping`: GET `/api/v1/inventory`.
- Returns all inventory items.

---

## Endpoint: Get Inventory Paginated

```java
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
```
- `@GetMapping("/paginated")`: GET `/api/v1/inventory/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Update Inventory Item

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('INVENTORY_UPDATE')")
public ResponseEntity<Object> updateItem(@PathVariable Long id, @Valid @RequestBody InventoryItemDTO dto) {
    InventoryItemResponseDTO response = inventoryService.updateItem(id, dto);
    return ResponseEntity.ok(ApiResponse.success("Item updated", response));
}
```
- `@PutMapping("/{id}")`: PUT `/api/v1/inventory/{id}`.
- Requires `INVENTORY_UPDATE` authority.
- Updates an inventory item by ID.

---

## Summary

- This controller manages inventory item creation, update, deletion, stock adjustment, and reporting.
- Enforces security via method-level authorization.
- Supports paginated and filtered inventory retrieval.
