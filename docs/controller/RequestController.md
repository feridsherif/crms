# RequestController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `RequestController` class, which manages waiter requests (create, acknowledge, resolve).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, JwtUtil, and RequestService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RequestService requestService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/requests")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects JwtUtil and RequestService.

---

## Endpoint: Acknowledge Request

```java
@PatchMapping("/{id}/acknowledge")
@PreAuthorize("hasAuthority('WAITER_REQUEST_UPDATE')")
public ResponseEntity<Object> acknowledgeRequest(@PathVariable Long id,
        @RequestBody com.hilcoe.crms.dto.WaiterActionDTO dto) {
    requestService.acknowledgeRequest(id, dto.getWaiterId());
    return ResponseEntity.ok(ApiResponse.success("Request acknowledged", null));
}
```
- `@PatchMapping("/{id}/acknowledge")`: PATCH `/api/v1/requests/{id}/acknowledge`.
- Requires `WAITER_REQUEST_UPDATE` authority.
- Acknowledges a waiter request by ID and waiter ID.

---

## Endpoint: Create Request

```java
@PostMapping
@PreAuthorize("hasAuthority('WAITER_REQUEST_CREATE')")
public ResponseEntity<Object> createRequest(@Valid @RequestBody WaiterRequestDTO dto,
        @RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(401).body(ApiResponse.error("Missing or invalid Authorization header"));
    }
    String token = authHeader.substring(7);
    Long userId = jwtUtil.extractUserId(token);
    if (userId == null) {
        return ResponseEntity.status(401).body(ApiResponse.error("User ID not found in token"));
    }
    WaiterRequestResponseDTO response = requestService.createRequest(dto, userId);
    return ResponseEntity.status(201).body(ApiResponse.success("Request created", response));
}
```
- `@PostMapping`: POST `/api/v1/requests`.
- Requires `WAITER_REQUEST_CREATE` authority.
- Validates JWT, extracts user ID, and creates a waiter request.

---

## Endpoint: Resolve Request

```java
@PatchMapping("/{id}/resolve")
@PreAuthorize("hasAuthority('WAITER_REQUEST_UPDATE')")
public ResponseEntity<Object> resolveRequest(@PathVariable Long id,
        @RequestBody com.hilcoe.crms.dto.WaiterActionDTO dto) {
    requestService.resolveRequest(id, dto.getWaiterId());
    return ResponseEntity.ok(ApiResponse.success("Request resolved", null));
}
```
- `@PatchMapping("/{id}/resolve")`: PATCH `/api/v1/requests/{id}/resolve`.
- Requires `WAITER_REQUEST_UPDATE` authority.
- Resolves a waiter request by ID and waiter ID.

---

## Summary

- This controller manages waiter request creation, acknowledgment, and resolution.
- Enforces security via JWT and method-level authorization.
- Uses DTOs for request and response payloads.
