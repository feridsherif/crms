# OrderStatusUpdateNotAllowedException Documentation

This document provides a line-by-line explanation of the `OrderStatusUpdateNotAllowedException` class, which is a custom exception for handling cases where an order's status cannot be updated in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class OrderStatusUpdateNotAllowedException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public OrderStatusUpdateNotAllowedException(Long id) {
        super("Order status update not allowed for order id: " + id);
    }
```
*Constructor that takes an order ID and creates an exception message indicating the status update is not allowed.*

```java
    public OrderStatusUpdateNotAllowedException(String message) {
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
- **Purpose:** Thrown when an order's status cannot be updated due to business rules or constraints.
- **Constructors:**
  - By order ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal that an order's status cannot be updated.
