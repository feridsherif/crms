# OrderController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `OrderController` class, which manages order creation, status updates, deletion, and retrieval.

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, JwtUtil, and OrderService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private OrderService orderService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/orders")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects JwtUtil and OrderService.

---

## Endpoint: Create Order

```java
@PostMapping
@PreAuthorize("hasAuthority('ORDER_CREATE')")
public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderCreateDTO dto,
        @RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(401).body(ApiResponse.error("Missing or invalid Authorization header"));
    }
    String token = authHeader.substring(7);
    Long userId = jwtUtil.extractUserId(token);
    if (userId == null) {
        return ResponseEntity.status(401).body(ApiResponse.error("User ID not found in token"));
    }
    OrderResponseDTO response = orderService.createOrder(dto, userId);
    return ResponseEntity.status(201).body(ApiResponse.success("Order created", response));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/orders`.
- Requires `ORDER_CREATE` authority.
- Validates JWT, extracts user ID, and creates an order.

---

## Endpoint: Delete Order

```java
@DeleteMapping("/{id}")
public ResponseEntity<Object> deleteOrder(@PathVariable Long id,
        @RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(401).body(ApiResponse.error("Missing or invalid Authorization header"));
    }
    String token = authHeader.substring(7);
    Long userId = jwtUtil.extractUserId(token);
    if (userId == null) {
        return ResponseEntity.status(401).body(ApiResponse.error("User ID not found in token"));
    }
    orderService.deleteOrder(id, userId);
    return ResponseEntity.ok(ApiResponse.success("Order deleted", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/orders/{id}`.
- Validates JWT, extracts user ID, and deletes the order.

---

## Endpoint: Get Order by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('ORDER_READ')")
public ResponseEntity<Object> getOrder(@PathVariable Long id) {
    OrderFullResponseDTO response = orderService.getOrder(id);
    return ResponseEntity.ok(ApiResponse.success("Order fetched", response));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/orders/{id}`.
- Requires `ORDER_READ` authority.
- Retrieves a full order by ID.

---

## Endpoint: Get Orders Paginated

```java
@GetMapping("/paginated")
public ResponseEntity<Object> getOrdersPaginated(@RequestParam(required = false) String status,
        @RequestParam(required = false) Long branchId, @RequestParam(required = false) Long tableId,
        @RequestParam(required = false) Long staffId,
        @RequestParam(required = false) java.math.BigDecimal minTotalAmount,
        @RequestParam(required = false) java.math.BigDecimal maxTotalAmount,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "orderId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    PaginatedResponseDTO<OrderResponseDTO> orders = orderService.getOrdersPaginatedAdvanced(status, branchId,
            tableId, staffId, minTotalAmount, maxTotalAmount, page, size, sortBy, direction);
    return ResponseEntity.ok(ApiResponse.success("Orders fetched", orders));
}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/orders/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Update Order Status

```java
@PatchMapping("/{id}/status")
@PreAuthorize("hasAuthority('ORDER_UPDATE')")
public ResponseEntity<Object> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusDTO dto,
        @RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(401).body(ApiResponse.error("Missing or invalid Authorization header"));
    }
    String token = authHeader.substring(7);
    Long userId = jwtUtil.extractUserId(token);
    if (userId == null) {
        return ResponseEntity.status(401).body(ApiResponse.error("User ID not found in token"));
    }
    OrderResponseDTO response = orderService.updateStatus(id, dto, userId);
    return ResponseEntity.ok(ApiResponse.success("Order status updated", response));
}
```
- `@PatchMapping("/{id}/status")`: Handles PATCH requests to `/api/v1/orders/{id}/status`.
- Requires `ORDER_UPDATE` authority.
- Updates the status of an order.

---

## Summary

- This controller manages order creation, deletion, status updates, and retrieval.
- Enforces security via JWT and method-level authorization.
- Supports paginated and filtered order retrieval.
