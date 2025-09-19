# RoleResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `RoleResponseDTO` class, which is used to transfer role data from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation annotations and Set type.

---

## Class Declaration

```java
public class RoleResponseDTO {
```
- Declares the DTO class for role response data.

---

## Fields

```java
@NotBlank
private String description;
@NotBlank
private String name;
private Set<Long> permissionIds;
private Set<String> permissionNames;
@NotNull
private Long roleId;
```
- `description`: Description of the role (required).
- `name`: Name of the role (required).
- `permissionIds`: Set of permission IDs assigned to the role.
- `permissionNames`: Set of permission names assigned to the role.
- `roleId`: Unique identifier for the role (required).

---

## Constructors

```java
public RoleResponseDTO() { ... }
public RoleResponseDTO(@NotNull Long roleId, @NotBlank String name, @NotBlank String description, Set<Long> permissionIds, Set<String> permissionNames) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending role data in API responses.
- Enforces validation for required fields.
- Contains all relevant info for a role, including permissions, in the API.
