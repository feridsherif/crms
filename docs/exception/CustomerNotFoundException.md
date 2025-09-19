# CustomerNotFoundException Documentation

This document provides a line-by-line explanation of the `CustomerNotFoundException` class, which is a custom exception for handling cases where a customer is not found in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class CustomerNotFoundException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public CustomerNotFoundException(Long id) {
        super("Customer not found with id: " + id);
    }
```
*Constructor that takes a customer ID and creates an exception message indicating the missing customer.*

```java
    public CustomerNotFoundException(String message) {
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
- **Purpose:** Thrown when a customer cannot be found by ID or other criteria.
- **Constructors:**
  - By ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal missing customer entries.
