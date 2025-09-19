# WaiterRequestNotFound Documentation

This document provides a line-by-line explanation of the `WaiterRequestNotFound` class, which is a custom exception for handling cases where a waiter request is not found in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class WaiterRequestNotFound extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public WaiterRequestNotFound(Long requestId) {
        super("Waiter request not found with id: " + requestId);
    }
```
*Constructor that takes a request ID and creates an exception message indicating the missing waiter request.*

```java
    public WaiterRequestNotFound(String message) {
        super(message);
    }
```
*Constructor that allows a custom exception message to be provided.*

```
}
```
*End of the class definition.*

---

## Summary
- **Purpose:** Thrown when a waiter request cannot be found by ID or other criteria.
- **Constructors:**
  - By request ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal missing waiter request entries.
