# StaffResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `StaffResponseDTO` class, which is used to transfer staff data from the server to the client, typically as a response object.

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
public class StaffResponseDTO {
```
- Declares the DTO class for staff response data.

---

## Fields

```java
@NotBlank
private String contact;
@NotNull
private Long roleId;
@NotNull
private Long staffId;
@NotNull
private Long userId;
```
- `contact`: Contact information for the staff member (required).
- `roleId`: The role assigned to the staff member (required).
- `staffId`: Unique identifier for the staff member (required).
- `userId`: The user account associated with the staff member (required).

---

## Constructors

```java
public StaffResponseDTO() { ... }
public StaffResponseDTO(@NotNull Long staffId, @NotNull Long userId, @NotNull Long roleId, @NotBlank String contact) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending staff data in API responses.
- Enforces validation for required fields.
- Contains all relevant info for a staff member, including IDs and contact, in the API.
