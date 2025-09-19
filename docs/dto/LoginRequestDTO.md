# LoginRequestDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `LoginRequestDTO` class, which is used to transfer login credentials from the client to the server.

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
public class LoginRequestDTO {
```
- Declares the DTO class for login requests.

---

## Fields

```java
@NotBlank
private String password;
@NotBlank
private String username;
```
- `@NotBlank`: Ensures the field is not null or empty.
- `password`: User's password.
- `username`: User's username.

---

## Getters and Setters

Standard JavaBean getters and setters for both fields.

---

## Summary
- Used for login requests.
- Enforces non-blank validation for both username and password.
