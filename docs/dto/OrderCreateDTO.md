# OrderCreateDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `OrderCreateDTO` class, which is used to transfer order creation data from the client to the server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation and list types.

---

## Class Declaration

```java
public class OrderCreateDTO {
```
- Declares the DTO class for order creation requests.

---

## Fields

```java
@NotNull
private Long branchId;
@NotNull
@NotEmpty
@Valid
private List<OrderItemDTO> items;
@NotNull
private Long tableId;
```
- `@NotNull` and `@NotEmpty`: Enforce required fields.
- `@Valid`: Ensures each item in the list is validated.
- `branchId`: The branch where the order is placed.
- `items`: List of order items (each an `OrderItemDTO`).
- `tableId`: The table for which the order is placed.

---

## Constructors

```java
public OrderCreateDTO() { ... }
public OrderCreateDTO(@NotNull Long branchId, @NotNull Long tableId, @NotNull List<OrderItemDTO> items) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for order creation requests.
- Enforces validation for all fields and nested order items.
- Contains all relevant info for creating an order in the API.
