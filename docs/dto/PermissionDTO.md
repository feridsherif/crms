# PermissionDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `PermissionDTO` class, which is used to transfer permission data between client and server.

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
public class PermissionDTO {
```
- Declares the DTO class for permission data.

---

## Fields

```java
private String description;
@NotBlank
private String name;
private Long permissionId;
```
- `description`: Description of the permission.
- `@NotBlank name`: Name of the permission (required).
- `permissionId`: Unique identifier for the permission.

---

## Constructors

```java
public PermissionDTO() { ... }
public PermissionDTO(Long permissionId, @NotBlank String name, String description) { ... }
public PermissionDTO(@NotBlank String name, String description) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for permission creation, update, and retrieval.
- Enforces non-blank validation for the name field.
- Contains all relevant info for a permission in the API.
