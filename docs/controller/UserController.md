# UserController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `UserController` class, which manages user accounts (CRUD, password reset, and pagination).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and UserService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/users")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the UserService.

---

## Endpoint: Create User

```java
@PostMapping
@PreAuthorize("hasAuthority('USER_CREATE')")
public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO dto) {
    UserDTO created = userService.createUser(dto);
    return ResponseEntity.status(201).body(ApiResponse.success("User created successfully", created));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/users`.
- Requires `USER_CREATE` authority.
- Creates a new user.

---

## Endpoint: Delete User

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('USER_DELETE')")
public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/users/{id}`.
- Requires `USER_DELETE` authority.
- Deletes a user by ID.

---

## Endpoint: Get All Users

```java
@GetMapping
@PreAuthorize("hasAuthority('USER_READ')")
public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
    List<UserDTO> users = userService.getAllUsers();
    return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/users`.
- Requires `USER_READ` authority.
- Returns all users.

---

## Endpoint: Get Paginated Users

```java
@GetMapping("/paginated")
@PreAuthorize("hasAuthority('USER_READ')")
public ResponseEntity<ApiResponse<PaginatedResponseDTO<UserDTO>>> getPaginatedUsers(
        @RequestParam(required = false) String username, @RequestParam(required = false) String email,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "userId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {
    PaginatedResponseDTO<UserDTO> paginatedUsers = userService.getPaginatedUsersAdvanced(username, email, page, size, sortBy, direction);
    return ResponseEntity.ok(ApiResponse.success("Paginated users retrieved successfully", paginatedUsers));
}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/users/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Get User by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('USER_READ')")
public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
    UserDTO user = userService.getUserById(id);
    return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/users/{id}`.
- Requires `USER_READ` authority.
- Returns a user by ID.

---

## Endpoint: Update User

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('USER_UPDATE')")
public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
    UserDTO updated = userService.updateUser(id, dto);
    return ResponseEntity.ok(ApiResponse.success("User updated successfully", updated));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/users/{id}`.
- Requires `USER_UPDATE` authority.
- Updates a user by ID.

---

## Endpoint: Reset User Password

```java
@PostMapping("/{id}/reset-password")
@PreAuthorize("hasAuthority('USER_UPDATE')")
public ResponseEntity<ApiResponse<Void>> resetPassword(@PathVariable Long id) {
    userService.resetPassword(id);
    return ResponseEntity.ok(ApiResponse.success("Password reset successfully", null));
}
```
- `@PostMapping("/{id}/reset-password")`: Handles POST requests to `/api/v1/users/{id}/reset-password`.
- Requires `USER_UPDATE` authority.
- Resets the password for a user by ID.

---

## Summary

- This controller manages user creation, update, deletion, password reset, and retrieval.
- Enforces security via method-level authorization.
- Supports paginated and filtered user retrieval.
