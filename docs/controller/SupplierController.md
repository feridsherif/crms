# SupplierController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `SupplierController` class, which manages suppliers (CRUD and pagination).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and SupplierService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/suppliers")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the SupplierService.

---

## Endpoint: Create Supplier

```java
@PostMapping
@PreAuthorize("hasAuthority('SUPPLIER_CREATE')")
public ResponseEntity<ApiResponse<SupplierDTO>> createSupplier(@Valid @RequestBody SupplierDTO dto) {
    SupplierDTO created = supplierService.createSupplier(dto);
    return ResponseEntity.status(201).body(ApiResponse.success("Supplier created successfully", created));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/suppliers`.
- Requires `SUPPLIER_CREATE` authority.
- Creates a new supplier.

---

## Endpoint: Delete Supplier

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('SUPPLIER_DELETE')")
public ResponseEntity<ApiResponse<Void>> deleteSupplier(@PathVariable Long id) {
    supplierService.deleteSupplier(id);
    return ResponseEntity.ok(ApiResponse.success("Supplier deleted successfully", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/suppliers/{id}`.
- Requires `SUPPLIER_DELETE` authority.
- Deletes a supplier by ID.

---

## Endpoint: Get All Suppliers

```java
@GetMapping
@PreAuthorize("hasAuthority('SUPPLIER_READ')")
public ResponseEntity<ApiResponse<List<SupplierDTO>>> getAllSuppliers() {
    List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
    return ResponseEntity.ok(ApiResponse.success("Suppliers retrieved successfully", suppliers));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/suppliers`.
- Requires `SUPPLIER_READ` authority.
- Returns all suppliers.

---

## Endpoint: Get Paginated Suppliers

```java
@GetMapping("/paginated")
@PreAuthorize("hasAuthority('SUPPLIER_READ')")
public ResponseEntity<ApiResponse<PaginatedResponseDTO<SupplierDTO>>> getPaginatedSuppliers(
        @RequestParam(required = false) String name, @RequestParam(required = false) String contact,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "supplierId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    PaginatedResponseDTO<SupplierDTO> paginatedSuppliers = supplierService.getPaginatedSuppliersAdvanced(name, contact, page, size, sortBy, direction);
    return ResponseEntity.ok(ApiResponse.success("Paginated suppliers retrieved successfully", paginatedSuppliers));
}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/suppliers/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Get Supplier by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('SUPPLIER_READ')")
public ResponseEntity<ApiResponse<SupplierDTO>> getSupplierById(@PathVariable Long id) {
    SupplierDTO supplier = supplierService.getSupplierById(id);
    return ResponseEntity.ok(ApiResponse.success("Supplier retrieved successfully", supplier));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/suppliers/{id}`.
- Requires `SUPPLIER_READ` authority.
- Returns a supplier by ID.

---

## Endpoint: Update Supplier

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('SUPPLIER_UPDATE')")
public ResponseEntity<ApiResponse<SupplierDTO>> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierDTO dto) {
    SupplierDTO updated = supplierService.updateSupplier(id, dto);
    return ResponseEntity.ok(ApiResponse.success("Supplier updated successfully", updated));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/suppliers/{id}`.
- Requires `SUPPLIER_UPDATE` authority.
- Updates a supplier by ID.

---

## Summary

- This controller manages supplier creation, update, deletion, and retrieval.
- Enforces security via method-level authorization.
- Supports paginated and filtered supplier retrieval.
