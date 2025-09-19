# OrderDeleteNotAllowedException Documentation

This document provides a line-by-line explanation of the `OrderDeleteNotAllowedException` class, which is a custom exception for handling cases where an order cannot be deleted in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class OrderDeleteNotAllowedException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public OrderDeleteNotAllowedException(Long id) {
        super("Order cannot be deleted for id: " + id);
    }
```
*Constructor that takes an order ID and creates an exception message indicating the order cannot be deleted.*

```java
    public OrderDeleteNotAllowedException(String message) {
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
- **Purpose:** Thrown when an order cannot be deleted due to business rules or constraints.
- **Constructors:**
  - By order ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal that an order cannot be deleted.
