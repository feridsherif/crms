# ReservationCreateDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `ReservationCreateDTO` class, which is used to transfer reservation creation data from the client to the server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation and date/time types.

---

## Class Declaration

```java
public class ReservationCreateDTO {
```
- Declares the DTO class for reservation creation requests.

---

## Fields

```java
@NotNull
private Long branchId;
@NotNull
private Long customerId;
@Min(1)
private int partySize;
@NotNull
private LocalDateTime reservationTime;
@NotNull
private Long tableId;
```
- `@NotNull` and `@Min(1)`: Enforce required fields and minimum party size.
- `branchId`: The branch where the reservation is made.
- `customerId`: The customer making the reservation.
- `partySize`: Number of people in the reservation (minimum 1).
- `reservationTime`: Date and time of the reservation.
- `tableId`: The table reserved.

---

## Constructors

```java
public ReservationCreateDTO() { ... }
public ReservationCreateDTO(@NotNull Long customerId, @NotNull Long tableId, @NotNull LocalDateTime reservationTime, @Min(1) int partySize, @NotNull Long branchId) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for reservation creation requests.
- Enforces validation for all fields.
- Contains all relevant info for creating a reservation in the API.
