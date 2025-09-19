# RoleDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `RoleDTO` class, which is used to transfer role data between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
```
- Declares the package and imports validation annotations and Set type.

---

## Class Declaration

```java
public class RoleDTO {
```
- Declares the DTO class for role data.

---

## Fields

```java
private String description;
@NotBlank
private String name;
private Set<Long> permissionIds;
private Long roleId;
```
- `description`: Description of the role.
- `@NotBlank name`: Name of the role (required).
- `permissionIds`: Set of permission IDs assigned to the role.
- `roleId`: Unique identifier for the role.

---

## Constructors

```java
public RoleDTO() { ... }
public RoleDTO(Long roleId, @NotBlank String name, String description, Set<Long> permissionIds) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for role creation, update, and retrieval.
- Enforces non-blank validation for the name field.
- Contains all relevant info for a role in the API, including permissions.
