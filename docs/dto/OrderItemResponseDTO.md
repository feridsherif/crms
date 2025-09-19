# OrderItemResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `OrderItemResponseDTO` class, which is used to transfer order item data from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation and numeric types.

---

## Class Declaration

```java
public class OrderItemResponseDTO {
```
- Declares the DTO class for order item response data.

---

## Fields

```java
@NotNull
private Long menuItemId;
@NotNull
private Long orderItemId;
@NotNull
private int quantity;
@NotNull
private BigDecimal unitPrice;
```
- `@NotNull`: Enforces required fields.
- `menuItemId`: The menu item being ordered.
- `orderItemId`: Unique identifier for the order item.
- `quantity`: The quantity of the menu item.
- `unitPrice`: The price per unit of the menu item.

---

## Constructors

```java
public OrderItemResponseDTO() { ... }
public OrderItemResponseDTO(@NotNull Long orderItemId, @NotNull Long menuItemId, @NotNull int quantity, @NotNull BigDecimal unitPrice) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending order item data in API responses.
- Enforces validation for all fields.
- Contains all relevant info for a single order item in an order response.
