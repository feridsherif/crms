# PermissionResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `PermissionResponseDTO` class, which is used to transfer permission data from the server to the client, typically as a response object.

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
public class PermissionResponseDTO {
```
- Declares the DTO class for permission response data.

---

## Fields

```java
@NotBlank
private String description;
@NotBlank
private String name;
@NotNull
private Long permissionId;
```
- `@NotBlank description`: Description of the permission (required).
- `@NotBlank name`: Name of the permission (required).
- `@NotNull permissionId`: Unique identifier for the permission (required).

---

## Constructors

```java
public PermissionResponseDTO() { ... }
public PermissionResponseDTO(@NotNull Long permissionId, @NotBlank String name, @NotBlank String description) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending permission data in API responses.
- Enforces validation for all fields.
- Contains all relevant info for a permission in the API.
