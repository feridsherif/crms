# AdminController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `AdminController` class, which manages admin-only endpoints such as fetching system reports.

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.service.AdminService;
```
- Imports Spring REST, security, and service classes.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/admin")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the AdminService.

---

## Endpoint: Get Reports

```java
@GetMapping("/reports")
@PreAuthorize("hasAuthority('ADMIN_READ')")
public ResponseEntity<Object> getReports() {
    Object report = adminService.getReports();
    return ResponseEntity.ok(ApiResponse.success("Reports fetched", report));
}
```
- `@GetMapping("/reports")`: Handles GET requests to `/api/v1/admin/reports`.
- `@PreAuthorize("hasAuthority('ADMIN_READ')")`: Only users with `ADMIN_READ` can access.
- Calls the service to fetch reports and returns them in a standardized response.

---

## Summary

- This controller provides a RESTful API for fetching admin reports.
- Security is enforced via method-level `@PreAuthorize` annotation.
- Uses a service layer for business logic.
