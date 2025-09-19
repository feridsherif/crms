# StockMovementDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `StockMovementDTO` class, which is used to transfer stock movement data (such as inventory changes) between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation and numeric types.

---

## Class Declaration

```java
public class StockMovementDTO {
```
- Declares the DTO class for stock movement data.

---

## Fields

```java
@NotNull
private Long createdBy;
@NotNull
private Long inventoryItemId;
private Long movementId;
@NotNull
private BigDecimal quantityChange;
@NotNull
private String reason;
```
- `createdBy`: The user who performed the stock movement (required).
- `inventoryItemId`: The inventory item affected (required).
- `movementId`: Unique identifier for the stock movement (optional, for updates or responses).
- `quantityChange`: The amount of stock changed (required).
- `reason`: The reason for the stock movement (required).

---

## Constructors

```java
public StockMovementDTO() { ... }
public StockMovementDTO(Long inventoryItemId, BigDecimal quantityChange, String reason, Long createdBy) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for stock movement requests (e.g., inventory increases or decreases).
- Enforces validation for required fields.
- Contains all relevant info for a stock movement in the API.
