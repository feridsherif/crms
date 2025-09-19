# AuditLogNotFoundException Documentation

This document provides a line-by-line explanation of the `AuditLogNotFoundException` class, which is a custom exception for handling cases where an audit log entry is not found in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class AuditLogNotFoundException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public AuditLogNotFoundException(Long id) {
        super("Audit log not found with id: " + id);
    }
```
*Constructor that takes an audit log ID and creates an exception message indicating the missing audit log.*

```java
    public AuditLogNotFoundException(String message) {
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
- **Purpose:** Thrown when an audit log entry cannot be found by ID or other criteria.
- **Constructors:**
  - By ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal missing audit log entries.
