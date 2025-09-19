# ShiftController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `ShiftController` class, which manages staff shifts (CRUD and pagination).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and ShiftService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/shifts")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the ShiftService.

---

## Endpoint: Create Shift

```java
@PostMapping
@PreAuthorize("hasAuthority('SHIFT_CREATE')")
public ResponseEntity<ApiResponse<ShiftDTO>> createShift(@Valid @RequestBody ShiftDTO dto) {
    ShiftDTO created = shiftService.createShift(dto);
    return ResponseEntity.status(201).body(ApiResponse.success("Shift created successfully", created));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/shifts`.
- Requires `SHIFT_CREATE` authority.
- Creates a new shift.

---

## Endpoint: Delete Shift

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('SHIFT_DELETE')")
public ResponseEntity<ApiResponse<Void>> deleteShift(@PathVariable Long id) {
    shiftService.deleteShift(id);
    return ResponseEntity.ok(ApiResponse.success("Shift deleted successfully", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/shifts/{id}`.
- Requires `SHIFT_DELETE` authority.
- Deletes a shift by ID.

---

## Endpoint: Get All Shifts

```java
@GetMapping
@PreAuthorize("hasAuthority('SHIFT_READ')")
public ResponseEntity<ApiResponse<List<ShiftDTO>>> getAllShifts() {
    List<ShiftDTO> shifts = shiftService.getAllShifts();
    return ResponseEntity.ok(ApiResponse.success("Shifts retrieved successfully", shifts));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/shifts`.
- Requires `SHIFT_READ` authority.
- Returns all shifts.

---

## Endpoint: Get Paginated Shifts

```java
@GetMapping("/paginated")
@PreAuthorize("hasAuthority('SHIFT_READ')")
public ResponseEntity<ApiResponse<PaginatedResponseDTO<ShiftDTO>>> getPaginatedShifts(
        @RequestParam(required = false) Long staffId, @RequestParam(required = false) Long branchId,
        @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "shiftId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    PaginatedResponseDTO<ShiftDTO> paginatedShifts = shiftService.getPaginatedShiftsAdvanced(staffId, branchId, startTime, endTime, page, size, sortBy, direction);
    return ResponseEntity.ok(ApiResponse.success("Paginated shifts retrieved successfully", paginatedShifts));
}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/shifts/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Get Shift by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('SHIFT_READ')")
public ResponseEntity<ApiResponse<ShiftDTO>> getShiftById(@PathVariable Long id) {
    ShiftDTO shift = shiftService.getShiftById(id);
    return ResponseEntity.ok(ApiResponse.success("Shift retrieved successfully", shift));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/shifts/{id}`.
- Requires `SHIFT_READ` authority.
- Returns a shift by ID.

---

## Endpoint: Update Shift

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('SHIFT_UPDATE')")
public ResponseEntity<ApiResponse<ShiftDTO>> updateShift(@PathVariable Long id, @Valid @RequestBody ShiftDTO dto) {
    ShiftDTO updated = shiftService.updateShift(id, dto);
    return ResponseEntity.ok(ApiResponse.success("Shift updated successfully", updated));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/shifts/{id}`.
- Requires `SHIFT_UPDATE` authority.
- Updates a shift by ID.

---

## Summary

- This controller manages shift creation, update, deletion, and retrieval.
- Enforces security via method-level authorization.
- Supports paginated and filtered shift retrieval.
