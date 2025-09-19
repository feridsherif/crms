# OrderResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `OrderResponseDTO` class, which is used to transfer order status and ID from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation annotations.

---

## Class Declaration

```java
public class OrderResponseDTO {
```
- Declares the DTO class for order response data.

---

## Fields

```java
@NotNull
private Long orderId;
@NotBlank
private String status;
```
- `@NotNull`: Ensures the order ID is provided.
- `@NotBlank`: Ensures the status is not null or empty.
- `orderId`: Unique identifier for the order.
- `status`: Status of the order (e.g., PENDING, COMPLETED).

---

## Constructors

```java
public OrderResponseDTO() { ... }
public OrderResponseDTO(@NotNull Long orderId, @NotBlank String status) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending order status and ID in API responses.
- Enforces validation for all fields.
- Contains only the essential info for order status updates and lookups.
