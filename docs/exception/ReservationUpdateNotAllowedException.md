# ReservationUpdateNotAllowedException Documentation

This document provides a line-by-line explanation of the `ReservationUpdateNotAllowedException` class, which is a custom exception for handling cases where a reservation cannot be updated in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class ReservationUpdateNotAllowedException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public ReservationUpdateNotAllowedException(Long id) {
        super("Reservation with id " + id + " cannot be updated because it is CANCELLED or COMPLETED.");
    }
```
*Constructor that takes a reservation ID and creates an exception message indicating the reservation cannot be updated because it is cancelled or completed.*

```java
    public ReservationUpdateNotAllowedException(String message) {
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
- **Purpose:** Thrown when a reservation cannot be updated due to its current status (e.g., cancelled or completed).
- **Constructors:**
  - By reservation ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal that a reservation cannot be updated.
