# CustomerAlreadyExistsForUserException Documentation

This document provides a line-by-line explanation of the `CustomerAlreadyExistsForUserException` class, which is a custom exception for handling cases where a customer already exists for a given user in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class CustomerAlreadyExistsForUserException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public CustomerAlreadyExistsForUserException(Long userId) {
        super("A customer already exists for user with id: " + userId);
    }
```
*Constructor that takes a user ID and creates an exception message indicating a customer already exists for that user.*

```java
    public CustomerAlreadyExistsForUserException(String message) {
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
- **Purpose:** Thrown when a customer already exists for a given user.
- **Constructors:**
  - By user ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal duplicate customer creation attempts for a user.
