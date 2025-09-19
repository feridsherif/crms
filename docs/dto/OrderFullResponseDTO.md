# OrderFullResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `OrderFullResponseDTO` class, which is used to transfer full order details from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation, numeric, and list types.

---

## Class Declaration

```java
public class OrderFullResponseDTO {
```
- Declares the DTO class for full order response data.

---

## Fields

```java
@NotNull
private Long branchId;
@NotNull
private List<OrderItemResponseDTO> items;
@NotNull
private Long orderId;
@NotNull
private Long staffId;
@NotBlank
private String status;
@NotNull
private Long tableId;
@NotNull
private BigDecimal totalAmount;
```
- `@NotNull` and `@NotBlank`: Enforce required fields.
- `branchId`: The branch where the order was placed.
- `items`: List of order items (each an `OrderItemResponseDTO`).
- `orderId`: Unique identifier for the order.
- `staffId`: The staff member who handled the order.
- `status`: Status of the order (e.g., PENDING, COMPLETED).
- `tableId`: The table for which the order was placed.
- `totalAmount`: Total amount for the order.

---

## Constructors

```java
public OrderFullResponseDTO() { ... }
public OrderFullResponseDTO(@NotNull Long orderId, @NotNull Long branchId, @NotNull Long tableId, @NotNull Long staffId, @NotBlank String status, @NotNull BigDecimal totalAmount, @NotNull List<OrderItemResponseDTO> items) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending full order details in API responses.
- Enforces validation for all fields.
- Contains all relevant info for a complete order, including items and totals.
