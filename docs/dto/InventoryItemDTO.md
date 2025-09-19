# InventoryItemDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `InventoryItemDTO` class, which is used to transfer inventory item data between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation and numeric types.

---

## Class Declaration

```java
public class InventoryItemDTO {
```
- Declares the DTO class for inventory item data.

---

## Fields

```java
@NotBlank
private String name;
@NotNull
private BigDecimal quantity;
@NotNull
private Long supplierId;
@NotNull
private BigDecimal threshold;
@NotBlank
private String unit;
```
- `@NotBlank` and `@NotNull`: Enforce required fields.
- `name`: Name of the inventory item.
- `quantity`: Current quantity in stock.
- `supplierId`: Associated supplier's ID.
- `threshold`: Minimum quantity before restock is needed.
- `unit`: Unit of measurement (e.g., kg, pcs).

---

## Constructors

```java
public InventoryItemDTO() { ... }
public InventoryItemDTO(String name, String unit, BigDecimal quantity, BigDecimal threshold, Long supplierId) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields, allowing encapsulated access and modification.

---

## Summary
- Used for inventory item creation and update requests.
- Enforces validation for all fields.
- Contains all relevant inventory item info for the API.
