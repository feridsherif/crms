# StaffController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `StaffController` class, which manages staff members (CRUD, role assignment, and pagination).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and StaffService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/staff")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the StaffService.

---

## Endpoint: Add Staff

```java
@PostMapping
@PreAuthorize("hasAuthority('STAFF_CREATE')")
public ResponseEntity<ApiResponse<StaffDTO>> addStaff(@Valid @RequestBody StaffDTO dto) {
    StaffDTO created = staffService.addStaff(dto);
    return ResponseEntity.status(201).body(ApiResponse.success("Staff added successfully", created));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/staff`.
- Requires `STAFF_CREATE` authority.
- Adds a new staff member.

---

## Endpoint: Delete Staff

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('STAFF_DELETE')")
public ResponseEntity<ApiResponse<Void>> removeStaff(@PathVariable Long id) {
    staffService.removeStaff(id);
    return ResponseEntity.ok(ApiResponse.success("Staff removed successfully", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/staff/{id}`.
- Requires `STAFF_DELETE` authority.
- Removes a staff member by ID.

---

## Endpoint: Get All Staff

```java
@GetMapping
@PreAuthorize("hasAuthority('STAFF_READ')")
public ResponseEntity<ApiResponse<List<StaffDTO>>> getAllStaff() {
    List<StaffDTO> staff = staffService.getAllStaff();
    return ResponseEntity.ok(ApiResponse.success("Staff retrieved successfully", staff));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/staff`.
- Requires `STAFF_READ` authority.
- Returns all staff members.

---

## Endpoint: Get Paginated Staff

```java
@GetMapping("/paginated")
@PreAuthorize("hasAuthority('STAFF_READ')")
public ResponseEntity<ApiResponse<PaginatedResponseDTO<StaffDTO>>> getPaginatedStaff(
        @RequestParam(required = false) Long roleId, @RequestParam(required = false) String contact,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "staffId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    PaginatedResponseDTO<StaffDTO> paginatedStaff = staffService.getPaginatedStaffAdvanced(roleId, contact, page, size, sortBy, direction);
    return ResponseEntity.ok(ApiResponse.success("Paginated staff retrieved successfully", paginatedStaff));
}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/staff/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Get Staff by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('STAFF_READ')")
public ResponseEntity<ApiResponse<StaffDTO>> getStaffById(@PathVariable Long id) {
    StaffDTO staff = staffService.getStaffById(id);
    return ResponseEntity.ok(ApiResponse.success("Staff retrieved successfully", staff));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/staff/{id}`.
- Requires `STAFF_READ` authority.
- Returns a staff member by ID.

---

## Endpoint: Update Staff

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('STAFF_UPDATE')")
public ResponseEntity<ApiResponse<StaffDTO>> updateStaff(@PathVariable Long id, @Valid @RequestBody StaffDTO dto) {
    StaffDTO updated = staffService.updateStaff(id, dto);
    return ResponseEntity.ok(ApiResponse.success("Staff updated successfully", updated));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/staff/{id}`.
- Requires `STAFF_UPDATE` authority.
- Updates a staff member by ID.

---

## Endpoint: Assign Role to Staff

```java
@PatchMapping("/{id}/role")
@PreAuthorize("hasAuthority('STAFF_UPDATE')")
public ResponseEntity<ApiResponse<Void>> assignRole(@PathVariable Long id, @RequestParam Long roleId) {
    staffService.assignRole(id, roleId);
    return ResponseEntity.ok(ApiResponse.success("Role assigned successfully", null));
}
```
- `@PatchMapping("/{id}/role")`: Handles PATCH requests to `/api/v1/staff/{id}/role`.
- Requires `STAFF_UPDATE` authority.
- Assigns a role to a staff member.

---

## Summary

- This controller manages staff creation, update, deletion, role assignment, and retrieval.
- Enforces security via method-level authorization.
- Supports paginated and filtered staff retrieval.
