# RoleController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `RoleController` class, which manages roles (CRUD and pagination) for the system.

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and RoleService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/admin/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
    @Autowired
    private RoleService roleService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/admin/roles")`: Sets the base URL for all endpoints in this controller.
- `@PreAuthorize("hasRole('ADMIN')")`: Restricts all endpoints to users with the ADMIN role.
- `@Autowired`: Injects the RoleService.

---

## Endpoint: Create Role

```java
@PostMapping
@PreAuthorize("hasAuthority('ROLE_CREATE')")
public ResponseEntity<ApiResponse<RoleDTO>> createRole(@Valid @RequestBody RoleDTO dto,
        Authentication authentication) {
    Long userId = Long.parseLong(authentication.getName());
    RoleDTO createdRole = roleService.createRole(dto, userId);
    return ResponseEntity.status(201).body(ApiResponse.success("Role created successfully", createdRole));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/admin/roles`.
- Requires `ROLE_CREATE` authority.
- Creates a new role, using the authenticated user's ID.

---

## Endpoint: Delete Role

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('ROLE_DELETE')")
public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable Long id, Authentication authentication) {
    Long userId = Long.parseLong(authentication.getName());
    roleService.deleteRole(id, userId);
    return ResponseEntity.ok(ApiResponse.success("Role deleted successfully", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/admin/roles/{id}`.
- Requires `ROLE_DELETE` authority.
- Deletes a role by ID, using the authenticated user's ID.

---

## Endpoint: Get All Roles

```java
@GetMapping
@PreAuthorize("hasAuthority('ROLE_READ')")
public ResponseEntity<ApiResponse<List<RoleResponseDTO>>> getAllRoles() {
    List<RoleResponseDTO> roles = roleService.getAllRoles();
    return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully", roles));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/admin/roles`.
- Requires `ROLE_READ` authority.
- Returns all roles.

---

## Endpoint: Get Role by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('ROLE_READ')")
public ResponseEntity<ApiResponse<RoleResponseDTO>> getRoleById(@PathVariable Long id) {
    RoleResponseDTO role = roleService.getRoleById(id);
    return ResponseEntity.ok(ApiResponse.success("Role retrieved successfully", role));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/admin/roles/{id}`.
- Requires `ROLE_READ` authority.
- Returns a role by ID.

---

## Endpoint: Get Roles Paginated

```java
@GetMapping("/paginated")
@PreAuthorize("hasAuthority('ROLE_READ')")
public ResponseEntity<ApiResponse<PaginatedResponseDTO<RoleDTO>>> getRolesPaginated(
        @RequestParam(required = false) String name, @RequestParam(required = false) String description,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "roleId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    PaginatedResponseDTO<RoleDTO> roles = roleService.getRolesPaginatedAdvanced(name, description, page, size,
            sortBy, direction);
    return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully", roles));
}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/admin/roles/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Update Role

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('ROLE_UPDATE')")
public ResponseEntity<ApiResponse<RoleDTO>> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDTO dto,
        Authentication authentication) {
    Long userId = Long.parseLong(authentication.getName());
    RoleDTO updatedRole = roleService.updateRole(id, dto, userId);
    return ResponseEntity.ok(ApiResponse.success("Role updated successfully", updatedRole));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/admin/roles/{id}`.
- Requires `ROLE_UPDATE` authority.
- Updates a role by ID, using the authenticated user's ID.

---

## Summary

- This controller manages role creation, update, deletion, and retrieval.
- Enforces security via method-level authorization and ADMIN role.
- Supports paginated and filtered role retrieval.
