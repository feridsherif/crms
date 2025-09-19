# UserDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `UserDTO` class, which is used to transfer user data between client and server (for user creation and updates).

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
public class UserDTO {
```
- Declares the DTO class for user data.

---

## Fields

```java
@NotBlank
private String email;
@NotBlank
private String password;
@NotNull
private Set<Long> roleIds;
private Long userId;
@NotBlank
private String username;
```
- `email`: User's email address (required).
- `password`: User's password (required).
- `roleIds`: Set of role IDs assigned to the user (required).
- `userId`: Unique identifier for the user (optional, for updates).
- `username`: User's username (required).

---

## Constructors

```java
public UserDTO() { ... }
public UserDTO(Long userId, @NotBlank String username, @NotBlank String email, @NotNull Set<Long> roleIds, @NotBlank String password) { ... }
public UserDTO(@NotBlank String username, @NotBlank String email, @NotNull Set<Long> roleIds, @NotBlank String password) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for user creation and update requests.
- Enforces validation for all required fields.
- Contains all relevant info for a user in the API, including roles.
