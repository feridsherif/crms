# ReservationController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `ReservationController` class, which manages reservations (create, update, cancel, complete, confirm, delete, get, and paginate).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, JwtUtil, and ReservationService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ReservationService reservationService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/reservations")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects JwtUtil and ReservationService.

---

## Endpoint: Cancel Reservation

```java
@PatchMapping("/{id}/cancel")
@PreAuthorize("hasAuthority('RESERVATION_UPDATE')")
public ResponseEntity<Object> cancelReservation(@PathVariable Long id,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    reservationService.cancelReservation(id, userId);
    return ResponseEntity.ok(ApiResponse.success("Reservation cancelled", null));
}
```
- `@PatchMapping("/{id}/cancel")`: PATCH `/api/v1/reservations/{id}/cancel`.
- Requires `RESERVATION_UPDATE` authority.
- Cancels a reservation by ID for the authenticated user.

---

## Endpoint: Complete Reservation

```java
@PatchMapping("/{id}/complete")
public ResponseEntity<Object> completeReservation(@PathVariable Long id,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    ReservationResponseDTO response = reservationService.completeReservation(id, userId);
    return ResponseEntity.ok(ApiResponse.success("Reservation completed", response));
}
```
- `@PatchMapping("/{id}/complete")`: PATCH `/api/v1/reservations/{id}/complete`.
- Completes a reservation by ID for the authenticated user.

---

## Endpoint: Confirm Reservation

```java
@PatchMapping("/{id}/confirm")
public ResponseEntity<Object> confirmReservation(@PathVariable Long id,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    ReservationResponseDTO response = reservationService.confirmReservation(id, userId);
    return ResponseEntity.ok(ApiResponse.success("Reservation confirmed", response));
}
```
- `@PatchMapping("/{id}/confirm")`: PATCH `/api/v1/reservations/{id}/confirm`.
- Confirms a reservation by ID for the authenticated user.

---

## Endpoint: Create Reservation

```java
@PostMapping
@PreAuthorize("hasAuthority('RESERVATION_CREATE')")
public ResponseEntity<Object> createReservation(@Valid @RequestBody ReservationCreateDTO dto,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    ReservationResponseDTO response = reservationService.createReservation(dto, userId);
    return ResponseEntity.status(201).body(ApiResponse.success("Reservation created", response));
}
```
- `@PostMapping`: POST `/api/v1/reservations`.
- Requires `RESERVATION_CREATE` authority.
- Creates a reservation for the authenticated user.

---

## Endpoint: Delete Reservation

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('RESERVATION_DELETE')")
public ResponseEntity<Object> deleteReservation(@PathVariable Long id,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    reservationService.deleteReservation(id, userId);
    return ResponseEntity.ok(ApiResponse.success("Reservation deleted", null));
}
```
- `@DeleteMapping("/{id}")`: DELETE `/api/v1/reservations/{id}`.
- Requires `RESERVATION_DELETE` authority.
- Deletes a reservation by ID for the authenticated user.

---

## Endpoint: Get Reservation by ID

```java
@GetMapping("/{id}")
public ResponseEntity<Object> getReservation(@PathVariable Long id) {
    ReservationFullResponseDTO reservation = reservationService.getReservation(id);
    return ResponseEntity.ok(ApiResponse.success("Reservation fetched", reservation));
}
```
- `@GetMapping("/{id}")`: GET `/api/v1/reservations/{id}`.
- Returns a reservation by ID.

---

## Endpoint: Get All Reservations

```java
@GetMapping
public ResponseEntity<Object> getReservations() {
    List<ReservationFullResponseDTO> reservations = reservationService.getReservations();
    return ResponseEntity.ok(ApiResponse.success("Reservations fetched", reservations));
}
```
- `@GetMapping`: GET `/api/v1/reservations`.
- Returns all reservations.

---

## Endpoint: Get Reservations Paginated

```java
@GetMapping("/paginated")
public ResponseEntity<Object> getReservationsPaginated(@RequestParam(required = false) String status,
        @RequestParam(required = false) Long branchId, @RequestParam(required = false) Long tableId,
        @RequestParam(required = false) Long customerId, @RequestParam(required = false) String minReservationTime,
        @RequestParam(required = false) String maxReservationTime,
        @RequestParam(required = false) Integer minPartySize, @RequestParam(required = false) Integer maxPartySize,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "reservationId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    LocalDateTime minTime = minReservationTime != null ? LocalDateTime.parse(minReservationTime) : null;
    LocalDateTime maxTime = maxReservationTime != null ? LocalDateTime.parse(maxReservationTime) : null;
    PaginatedResponseDTO<ReservationResponseDTO> reservations = reservationService.getReservationsPaginatedAdvanced(
            status, branchId, tableId, customerId, minTime, maxTime, minPartySize, maxPartySize, page, size, sortBy,
            direction);
    return ResponseEntity.ok(ApiResponse.success("Reservations fetched", reservations));
}
```
- `@GetMapping("/paginated")`: GET `/api/v1/reservations/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Update Reservation

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('RESERVATION_UPDATE')")
public ResponseEntity<Object> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationUpdateDTO dto,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    ReservationResponseDTO response = reservationService.updateReservation(id, dto, userId);
    return ResponseEntity.ok(ApiResponse.success("Reservation updated", response));
}
```
- `@PutMapping("/{id}")`: PUT `/api/v1/reservations/{id}`.
- Requires `RESERVATION_UPDATE` authority.
- Updates a reservation by ID for the authenticated user.

---

## Summary

- This controller manages reservation creation, update, cancellation, completion, confirmation, deletion, and retrieval.
- Enforces security via JWT and method-level authorization.
- Supports paginated and filtered reservation retrieval.
