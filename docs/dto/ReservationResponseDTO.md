# ReservationResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `ReservationResponseDTO` class, which is used to transfer reservation status and ID from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation annotations.

---

## Class Declaration

```java
public class ReservationResponseDTO {
```
- Declares the DTO class for reservation response data.

---

## Fields

```java
@NotNull
private Long reservationId;
@NotBlank
private String status;
```
- `@NotNull`: Ensures the reservation ID is provided.
- `@NotBlank`: Ensures the status is not null or empty.
- `reservationId`: Unique identifier for the reservation.
- `status`: Status of the reservation (e.g., CONFIRMED, CANCELLED).

---

## Constructors

```java
public ReservationResponseDTO() { ... }
public ReservationResponseDTO(@NotNull Long reservationId, @NotBlank String status) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending reservation status and ID in API responses.
- Enforces validation for all fields.
- Contains only the essential info for reservation status updates and lookups.
