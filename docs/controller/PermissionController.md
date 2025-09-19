# PermissionController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `PermissionController` class, which manages permissions for the system (CRUD and pagination).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and PermissionService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/admin/permissions")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/admin/permissions")`: Sets the base URL for all endpoints in this controller.
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts all endpoints to users with the ADMIN role.
- `@Autowired`: Injects the PermissionService.

---

## Endpoint: Create Permission

```java
@PostMapping
@PreAuthorize("hasAuthority('PERMISSION_CREATE')")
public ResponseEntity<ApiResponse<PermissionDTO>> createPermission(@Valid @RequestBody PermissionDTO dto) {
    PermissionDTO createdPermission = permissionService.createPermission(dto);
    return ResponseEntity.status(201)
            .body(ApiResponse.success("Permission created successfully", createdPermission));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/admin/permissions`.
- Requires `PERMISSION_CREATE` authority.
- Creates a new permission.

---

## Endpoint: Delete Permission

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('PERMISSION_DELETE')")
public ResponseEntity<ApiResponse<Void>> deletePermission(@PathVariable Long id) {
    permissionService.deletePermission(id);
    return ResponseEntity.ok(ApiResponse.success("Permission deleted successfully", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/admin/permissions/{id}`.
- Requires `PERMISSION_DELETE` authority.
- Deletes a permission by ID.

---

## Endpoint: Get All Permissions

```java
@GetMapping
@PreAuthorize("hasAuthority('PERMISSION_READ')")
public ResponseEntity<ApiResponse<List<PermissionDTO>>> getAllPermissions() {
    List<PermissionDTO> permissions = permissionService.getAllPermissions();
    return ResponseEntity.ok(ApiResponse.success("Permissions retrieved successfully", permissions));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/admin/permissions`.
- Requires `PERMISSION_READ` authority.
- Returns all permissions.

---

## Endpoint: Get Permission by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('PERMISSION_READ')")
public ResponseEntity<ApiResponse<PermissionDTO>> getPermissionById(@PathVariable Long id) {
    PermissionDTO permission = permissionService.getPermissionById(id);
    return ResponseEntity.ok(ApiResponse.success("Permission retrieved successfully", permission));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/admin/permissions/{id}`.
- Requires `PERMISSION_READ` authority.
- Returns a permission by ID.

---

## Endpoint: Get Permissions Paginated

```java
@GetMapping("/paginated")
@PreAuthorize("hasAuthority('PERMISSION_READ')")
public ResponseEntity<ApiResponse<PaginatedResponseDTO<PermissionDTO>>> getPermissionsPaginated(
        @RequestParam(required = false) String name, @RequestParam(required = false) String description,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "permissionId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    PaginatedResponseDTO<PermissionDTO> permissions = permissionService.getPermissionsPaginatedAdvanced(name,
            description, page, size, sortBy, direction);
    return ResponseEntity.ok(ApiResponse.success("Permissions retrieved successfully", permissions));
}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/admin/permissions/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Update Permission

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
public ResponseEntity<ApiResponse<PermissionDTO>> updatePermission(@PathVariable Long id,
        @Valid @RequestBody PermissionDTO dto) {
    PermissionDTO updatedPermission = permissionService.updatePermission(id, dto);
    return ResponseEntity.ok(ApiResponse.success("Permission updated successfully", updatedPermission));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/admin/permissions/{id}`.
- Requires `PERMISSION_UPDATE` authority.
- Updates a permission by ID.

---

## Summary

- This controller manages permission CRUD and paginated retrieval.
- All endpoints are restricted to ADMIN role and specific authorities.
- Uses DTOs for request and response payloads.
