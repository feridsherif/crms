# UserAlreadyExistsException Documentation

This document provides a line-by-line explanation of the `UserAlreadyExistsException` class, which is a custom exception for handling cases where a user already exists in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class UserAlreadyExistsException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public UserAlreadyExistsException(Long userId) {
        super("A user already exists with id: " + userId);
    }
```
*Constructor that takes a user ID and creates an exception message indicating a user already exists with that ID.*

```java
    public UserAlreadyExistsException(String message) {
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
- **Purpose:** Thrown when a user already exists with the given ID or criteria.
- **Constructors:**
  - By user ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal duplicate user creation attempts.
