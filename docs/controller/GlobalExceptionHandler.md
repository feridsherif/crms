# GlobalExceptionHandler.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `GlobalExceptionHandler` class, which manages global exception handling for all controllers in the CRMS system.

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Logger, Spring, and exception imports
```
- Imports logging, Spring, and custom exception classes.

---

## Class Declaration

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
```
- `@ControllerAdvice`: Applies this handler to all controllers.
- Logger for error logging.

---

## Exception Handlers

### Access Denied
```java
@ExceptionHandler(AccessDeniedException.class)
public ResponseEntity<ApiResponse<String>> handleAccessDeniedException(AccessDeniedException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.error("Access denied: " + ex.getMessage()));
}
```
- Handles forbidden access attempts.

### All Other Exceptions
```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ApiResponse<String>> handleAllOtherExceptions(Exception ex) {
    logger.error("Unhandled exception", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse
            .error("An unexpected error occurred: " + ex.getClass().getSimpleName() + ": " + ex.getMessage()));
}
```
- Handles any unhandled exceptions, logs them, and returns a generic error message.

### Authentication Exception
```java
@ExceptionHandler(AuthenticationException.class)
public ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error("Authentication failed: " + ex.getMessage()));
}
```
- Handles authentication failures.

### Conflict Exceptions
```java
@ExceptionHandler({ UserAlreadyExistsException.class, CustomerAlreadyExistsForUserException.class })
public ResponseEntity<ApiResponse<String>> handleConflictException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(ex.getMessage()));
}
```
- Handles resource conflict errors (e.g., duplicate users).

### JWT Exception
```java
@ExceptionHandler(JwtException.class)
public ResponseEntity<ApiResponse<String>> handleJwtException(JwtException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error("Invalid or expired JWT: " + ex.getMessage()));
}
```
- Handles JWT authentication errors.

### Method Not Supported
```java
@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
public ResponseEntity<ApiResponse<String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(ApiResponse.error("Method not allowed: " + ex.getMessage()));
}
```
- Handles HTTP method not allowed errors.

### Not Found Exceptions
```java
@ExceptionHandler({ ShiftNotFoundException.class, BranchNotFoundException.class, CategoryNotFoundException.class,
        CustomerNotFoundException.class, MenuItemNotFoundException.class, ReservationNotFoundException.class,
        StaffNotFoundException.class, InventoryItemNotFoundException.class, OrderNotFoundException.class,
        UserNotFoundException.class, PermissionNotFoundException.class, RoleNotFoundException.class,
        SupplierNotFoundException.class, WaiterRequestNotFound.class, RestaurantTableNotFoundException.class })
public ResponseEntity<ApiResponse<String>> handleNotFoundException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage()));
}
```
- Handles all resource not found errors.

### Reservation Conflict Exceptions
```java
@ExceptionHandler({ ReservationCancellationNotAllowedException.class, ReservationUpdateNotAllowedException.class,
        ReservationStatusUpdateNotAllowedException.class })
public ResponseEntity<ApiResponse<String>> handleReservationConflictException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(ex.getMessage()));
}
```
- Handles reservation-specific conflict errors.

### Validation Errors
```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
        MethodArgumentNotValidException ex) {
    Map<String, String> errors = new java.util.HashMap<>();
    for (org.springframework.validation.FieldError error : ex.getBindingResult().getFieldErrors()) {
        errors.put(error.getField(), error.getDefaultMessage());
    }
    return ResponseEntity.badRequest().body(ApiResponse.error("Validation failed", errors));
}
```
- Handles validation errors and returns field-specific error messages.

---

## Summary

- This class provides centralized exception handling for all controllers.
- Maps exceptions to appropriate HTTP status codes and standardized API responses.
- Improves error reporting and debugging for the entire system.
