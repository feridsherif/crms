# InventoryItemResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `InventoryItemResponseDTO` class, which is used to transfer inventory item data from the server to the client, typically as a response object.

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
public class InventoryItemResponseDTO {
```
- Declares the DTO class for inventory item response data.

---

## Fields

```java
@NotNull
private Long inventoryItemId;
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
- `@NotNull` and `@NotBlank`: Enforce required fields.
- `inventoryItemId`: Unique identifier for the inventory item.
- `name`: Name of the inventory item.
- `quantity`: Current quantity in stock.
- `supplierId`: Associated supplier's ID.
- `threshold`: Minimum quantity before restock is needed.
- `unit`: Unit of measurement (e.g., kg, pcs).

---

## Constructors

```java
public InventoryItemResponseDTO() { ... }
public InventoryItemResponseDTO(@NotNull Long inventoryItemId, @NotBlank String name, @NotBlank String unit, @NotNull BigDecimal quantity, @NotNull BigDecimal threshold, @NotNull Long supplierId) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields, allowing encapsulated access and modification.

---

## Summary
- Used for sending inventory item data in API responses.
- Enforces validation for all fields.
- Contains the unique inventory item ID and all relevant info for the API.
