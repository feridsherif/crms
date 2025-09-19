# OrderItemDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `OrderItemDTO` class, which is used to transfer order item data as part of an order creation request.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation annotations.

---

## Class Declaration

```java
public class OrderItemDTO {
```
- Declares the DTO class for order item data.

---

## Fields

```java
@NotNull
private Long menuItemId;
@Min(1)
private int quantity;
```
- `@NotNull`: Ensures the menu item ID is provided.
- `@Min(1)`: Ensures the quantity is at least 1.
- `menuItemId`: The menu item being ordered.
- `quantity`: The quantity of the menu item.

---

## Constructors

```java
public OrderItemDTO() { ... }
public OrderItemDTO(@NotNull Long menuItemId, @Min(1) int quantity) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for specifying items in an order creation request.
- Enforces validation for menu item ID and quantity.
- Contains all relevant info for a single order item.
