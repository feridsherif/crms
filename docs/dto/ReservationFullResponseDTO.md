# ReservationFullResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `ReservationFullResponseDTO` class, which is used to transfer full reservation details from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation and date/time types.

---

## Class Declaration

```java
public class ReservationFullResponseDTO {
```
- Declares the DTO class for full reservation response data.

---

## Fields

```java
@NotNull
private Long branchId;
@NotNull
private Long customerId;
@NotNull
private int partySize;
@NotNull
private Long reservationId;
@NotNull
private LocalDateTime reservationTime;
@NotBlank
private String status;
@NotNull
private Long tableId;
```
- `@NotNull` and `@NotBlank`: Enforce required fields.
- `branchId`: The branch where the reservation was made.
- `customerId`: The customer who made the reservation.
- `partySize`: Number of people in the reservation.
- `reservationId`: Unique identifier for the reservation.
- `reservationTime`: Date and time of the reservation.
- `status`: Status of the reservation (e.g., CONFIRMED, CANCELLED).
- `tableId`: The table reserved.

---

## Constructors

```java
public ReservationFullResponseDTO() { ... }
public ReservationFullResponseDTO(@NotNull Long reservationId, @NotNull Long customerId, @NotNull Long tableId, @NotNull Long branchId, @NotNull LocalDateTime reservationTime, @NotNull int partySize, @NotBlank String status) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending full reservation details in API responses.
- Enforces validation for all fields.
- Contains all relevant info for a complete reservation, including IDs, time, party size, and status.
