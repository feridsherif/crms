# StockMovementResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `StockMovementResponseDTO` class, which is used to transfer stock movement data from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation and numeric types.

---

## Class Declaration

```java
public class StockMovementResponseDTO {
```
- Declares the DTO class for stock movement response data.

---

## Fields

```java
@NotNull
private Long createdBy;
@NotNull
private Long inventoryItemId;
@NotNull
private Long movementId;
@NotNull
private BigDecimal quantityChange;
@NotBlank
private String reason;
```
- `createdBy`: The user who performed the stock movement (required).
- `inventoryItemId`: The inventory item affected (required).
- `movementId`: Unique identifier for the stock movement (required).
- `quantityChange`: The amount of stock changed (required).
- `reason`: The reason for the stock movement (required, must not be blank).

---

## Constructors

```java
public StockMovementResponseDTO() { ... }
public StockMovementResponseDTO(@NotNull Long movementId, @NotNull Long inventoryItemId, @NotNull BigDecimal quantityChange, @NotBlank String reason, @NotNull Long createdBy) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending stock movement data in API responses.
- Enforces validation for all fields.
- Contains all relevant info for a stock movement, including who performed it, in the API.
