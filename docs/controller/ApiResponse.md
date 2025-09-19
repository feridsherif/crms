# ApiResponse.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `ApiResponse` class, which is a generic response wrapper used in the CRMS system's controllers to standardize API responses.

---

## Package Declaration

```java
package com.hilcoe.crms.controller;
```
- Declares that this class is part of the `com.hilcoe.crms.controller` package.

---

## Class Declaration

```java
public class ApiResponse<T> {
```
- Declares a public, generic class `ApiResponse` with a type parameter `T` for the response data.

---

## Static Factory Methods

These methods provide convenient ways to create standardized response objects for different scenarios.

### Error Responses

```java
public static <T> ApiResponse<T> error(String message) {
    return new ApiResponse<>("error", message, null);
}
```
- Creates an error response with a message and no data.

```java
public static <T> ApiResponse<T> error(String message, T data) {
    return new ApiResponse<>("error", message, data);
}
```
- Creates an error response with a message and optional data.

### Failed Responses

```java
public static <T> ApiResponse<T> failed(String message) {
    return new ApiResponse<>("failed", message, null);
}
```
- Creates a failed response (distinct from error) with a message and no data.

```java
public static <T> ApiResponse<T> failed(String message, T data) {
    return new ApiResponse<>("failed", message, data);
}
```
- Creates a failed response with a message and optional data.

### Success Responses

```java
public static <T> ApiResponse<T> success(String message, T data) {
    return new ApiResponse<>("success", message, data);
}
```
- Creates a success response with a message and data.

```java
public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>("success", null, data);
}
```
- Creates a success response with data and no message.

---

## Fields

```java
private T data;
private String message;
private String status;
```
- `data`: The payload of the response (generic type `T`).
- `message`: A human-readable message describing the result.
- `status`: The status of the response (e.g., "success", "error", "failed").

---

## Constructor

```java
public ApiResponse(String status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
}
```
- Initializes the response with the given status, message, and data.

---

## Getters

```java
public T getData() {
    return data;
}

public String getMessage() {
    return message;
}

public String getStatus() {
    return status;
}
```
- Standard getter methods for each field, allowing read access to the response properties.

---

## Setters

```java
public void setData(T data) {
    this.data = data;
}

public void setMessage(String message) {
    this.message = message;
}

public void setStatus(String status) {
    this.status = status;
}
```
- Standard setter methods for each field, allowing modification of the response properties.

---

## Summary

- The `ApiResponse` class is a generic, reusable wrapper for API responses.
- It provides static factory methods for common response types (success, error, failed).
- It standardizes the structure of API responses with `status`, `message`, and `data` fields.
- Used throughout controllers to ensure consistent API output.
