# RestaurantTableController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `RestaurantTableController` class, which manages restaurant tables (CRUD and pagination).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and RestaurantTableService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/restaurant-tables")
public class RestaurantTableController {
    @Autowired
    private RestaurantTableService restaurantTableService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/restaurant-tables")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the RestaurantTableService.

---

## Endpoint: Create Table

```java
@PostMapping
@PreAuthorize("hasAuthority('TABLE_CREATE')")
public ResponseEntity<ApiResponse<RestaurantTableDTO>> createTable(@Valid @RequestBody RestaurantTableDTO dto) {
    RestaurantTableDTO created = restaurantTableService.createTable(dto);
    return ResponseEntity.status(201).body(ApiResponse.success("Table created successfully", created));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/restaurant-tables`.
- Requires `TABLE_CREATE` authority.
- Creates a new restaurant table.

---

## Endpoint: Delete Table

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('TABLE_DELETE')")
public ResponseEntity<ApiResponse<Void>> deleteTable(@PathVariable Long id) {
    restaurantTableService.deleteTable(id);
    return ResponseEntity.ok(ApiResponse.success("Table deleted successfully", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/restaurant-tables/{id}`.
- Requires `TABLE_DELETE` authority.
- Deletes a table by ID.

---

## Endpoint: Get All Tables

```java
@GetMapping
@PreAuthorize("hasAuthority('TABLE_READ')")
public ResponseEntity<ApiResponse<List<RestaurantTableDTO>>> getAllTables() {
    List<RestaurantTableDTO> tables = restaurantTableService.getAllTables();
    return ResponseEntity.ok(ApiResponse.success("Tables retrieved successfully", tables));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/restaurant-tables`.
- Requires `TABLE_READ` authority.
- Returns all tables.

---

## Endpoint: Get Paginated Tables

```java
@GetMapping("/paginated")
@PreAuthorize("hasAuthority('TABLE_READ')")
public ResponseEntity<ApiResponse<PaginatedResponseDTO<RestaurantTableDTO>>> getPaginatedTables(
        @RequestParam(required = false) Integer tableNumber, @RequestParam(required = false) Long branchId,
        @RequestParam(required = false) String status, @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "tableId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    PaginatedResponseDTO<RestaurantTableDTO> paginatedTables = restaurantTableService
            .getPaginatedTablesAdvanced(tableNumber, branchId, status, page, size, sortBy, direction);
    return ResponseEntity.ok(ApiResponse.success("Paginated tables retrieved successfully", paginatedTables));
}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/restaurant-tables/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Get Table by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('TABLE_READ')")
public ResponseEntity<ApiResponse<RestaurantTableDTO>> getTableById(@PathVariable Long id) {
    RestaurantTableDTO table = restaurantTableService.getTableById(id);
    return ResponseEntity.ok(ApiResponse.success("Table retrieved successfully", table));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/restaurant-tables/{id}`.
- Requires `TABLE_READ` authority.
- Returns a table by ID.

---

## Endpoint: Update Table

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('TABLE_UPDATE')")
public ResponseEntity<ApiResponse<RestaurantTableDTO>> updateTable(@PathVariable Long id,
        @Valid @RequestBody RestaurantTableDTO dto) {
    RestaurantTableDTO updated = restaurantTableService.updateTable(id, dto);
    return ResponseEntity.ok(ApiResponse.success("Table updated successfully", updated));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/restaurant-tables/{id}`.
- Requires `TABLE_UPDATE` authority.
- Updates a table by ID.

---

## Summary

- This controller manages restaurant table creation, update, deletion, and retrieval.
- Enforces security via method-level authorization.
- Supports paginated and filtered table retrieval.
