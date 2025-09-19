# UserResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `UserResponseDTO` class, which is used to transfer user data from the server to the client, typically as a response object.

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
public class UserResponseDTO {
```
- Declares the DTO class for user response data.

---

## Fields

```java
@NotBlank
private String email;
@NotNull
private Set<Long> roleIds;
@NotNull
private Long userId;
@NotBlank
private String username;
```
- `email`: User's email address (required).
- `roleIds`: Set of role IDs assigned to the user (required).
- `userId`: Unique identifier for the user (required).
- `username`: User's username (required).

---

## Constructors

```java
public UserResponseDTO() { ... }
public UserResponseDTO(@NotNull Long userId, @NotBlank String username, @NotBlank String email, @NotNull Set<Long> roleIds) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending user data in API responses.
- Enforces validation for all required fields.
- Contains all relevant info for a user, including roles, in the API.
