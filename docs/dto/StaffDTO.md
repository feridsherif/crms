# StaffDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `StaffDTO` class, which is used to transfer staff data between client and server (for staff creation and updates).

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation annotations.

---

## Class Declaration

```java
public class StaffDTO {
```
- Declares the DTO class for staff data.

---

## Fields

```java
@NotBlank
private String contact;
@NotNull
private Long roleId;
@NotNull
private Long userId;
```
- `contact`: Contact information for the staff member (required).
- `roleId`: The role assigned to the staff member (required).
- `userId`: The user account associated with the staff member (required).

---

## Constructors

```java
public StaffDTO() { ... }
public StaffDTO(@NotNull Long userId, @NotNull Long roleId, @NotBlank String contact) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for staff creation and update requests.
- Enforces validation for required fields.
- Contains all relevant info for a staff member in the API.
