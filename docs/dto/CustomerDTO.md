# CustomerDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `CustomerDTO` class, which is used to transfer customer data between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Size;
```
- Declares the package and imports for date/time and validation.

---

## Class Declaration

```java
public class CustomerDTO {
```
- Declares the DTO class for customer data.

---

## Fields

```java
private LocalDateTime createdAt;
private Long customerId;
@Size(max = 100)
private String loyaltyId;
private String name;
private String notes;
private String phone;
private LocalDateTime updatedAt;
private Long userId;
```
- `createdAt`, `updatedAt`: Timestamps for creation and last update.
- `customerId`: Unique identifier for the customer.
- `@Size(max = 100) loyaltyId`: Loyalty program ID, max 100 characters.
- `name`: Customer's name.
- `notes`: Additional notes about the customer.
- `phone`: Customer's phone number.
- `userId`: Associated user ID (account owner).

---

## Getters and Setters

Standard JavaBean getters and setters for all fields, allowing encapsulated access and modification.

---

## Summary
- Used for customer creation, update, and retrieval.
- Supports validation for loyalty ID length.
- Contains timestamps, IDs, and all relevant customer info.
