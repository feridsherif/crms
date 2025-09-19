# ReservationUpdateDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `ReservationUpdateDTO` class, which is used to transfer reservation update data from the client to the server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation and date/time types.

---

## Class Declaration

```java
public class ReservationUpdateDTO {
```
- Declares the DTO class for reservation update requests.

---

## Fields

```java
@Min(1)
private int partySize;
@NotNull
private LocalDateTime reservationTime;
```
- `@Min(1) partySize`: Number of people in the reservation (minimum 1).
- `@NotNull reservationTime`: Date and time of the reservation (required).

---

## Constructors

```java
public ReservationUpdateDTO() { ... }
public ReservationUpdateDTO(@NotNull LocalDateTime reservationTime, @Min(1) int partySize) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for reservation update requests.
- Enforces validation for all fields.
- Contains only the fields relevant for updating a reservation.
