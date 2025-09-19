# LoginResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `LoginResponseDTO` class, which is used to transfer authentication and user info from the server to the client after a successful login.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.util.List;
```
- Declares the package and imports the List type for permissions.

---

## Class Declaration

```java
public class LoginResponseDTO {
```
- Declares the DTO class for login responses.

---

## Fields

```java
private List<String> permissions;
private Long roleId;
private String token;
private Long userId;
private String username;
```
- `permissions`: List of permission strings granted to the user.
- `roleId`: The user's role ID.
- `token`: The JWT token for authentication.
- `userId`: The user's unique ID.
- `username`: The user's username.

---

## Constructors

```java
public LoginResponseDTO() { ... }
public LoginResponseDTO(String token, String username, Long userId, Long roleId, List<String> permissions) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending authentication and user info after login.
- Contains JWT, user ID, username, role, and permissions.
