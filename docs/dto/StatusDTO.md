# StatusDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `StatusDTO` class, which is used to transfer status updates (such as for orders or reservations) between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import jakarta.validation.constraints.NotBlank;
```
- Declares the package and imports validation annotations.

---

## Class Declaration

```java
public class StatusDTO {
```
- Declares the DTO class for status updates.

---

## Fields

```java
@NotBlank
private String status;
```
- `status`: The new status value (required, must not be blank).

---

## Constructors

```java
public StatusDTO() { ... }
public StatusDTO(String status) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for the status field.

---

## Summary
- Used for status update requests (e.g., order or reservation status changes).
- Enforces non-blank validation for the status field.
