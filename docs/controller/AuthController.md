# AuthController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `AuthController` class, which manages authentication endpoints (login and logout).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.LoginRequestDTO;
import com.hilcoe.crms.dto.LoginResponseDTO;
import com.hilcoe.crms.security.AuthService;

import jakarta.validation.Valid;
```
- Imports Spring REST, DTOs, and AuthService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
```
- `@RestController`: REST controller.
- `@RequestMapping("/api/v1/auth")`: Base path for auth endpoints.
- `@Autowired`: Injects AuthService.

---

## Endpoint: Login

```java
@PostMapping("/login")
public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
    try {
        LoginResponseDTO response = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    } catch (Exception e) {
        return ResponseEntity.status(401).body(ApiResponse.failed(e.getMessage()));
    }
}
```
- `@PostMapping("/login")`: POST `/api/v1/auth/login`.
- Validates and processes login, returns JWT and user info on success, error on failure.

---

## Endpoint: Logout

```java
@PostMapping("/logout")
public ResponseEntity<Object> logout(@RequestHeader(value = "Authorization", required = false) String token) {
    authService.logout(token);
    return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
}
```
- `@PostMapping("/logout")`: POST `/api/v1/auth/logout`.
- Accepts JWT token in header, calls logout logic, returns success.

---

## Summary

- This controller manages authentication (login/logout).
- Uses DTOs for request and response payloads.
- Handles errors and returns standardized API responses.
