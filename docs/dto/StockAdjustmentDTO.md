# StockAdjustmentDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `StockAdjustmentDTO` class, which is used to transfer stock adjustment data (such as for inventory changes) between client and server.

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
public class StockAdjustmentDTO {
```
- Declares the DTO class for stock adjustment data.

---

## Fields

```java
@NotNull
private BigDecimal quantityChange;
@NotNull
private String reason;
```
- `quantityChange`: The amount to adjust the stock by (required).
- `reason`: The reason for the stock adjustment (required).

---

## Constructors

```java
public StockAdjustmentDTO() { ... }
public StockAdjustmentDTO(BigDecimal quantityChange, String reason) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for stock adjustment requests (e.g., inventory increases or decreases).
- Enforces validation for all fields.
- Contains all relevant info for a stock adjustment in the API.
