# ReservationStatusUpdateNotAllowedException Documentation

This document provides a line-by-line explanation of the `ReservationStatusUpdateNotAllowedException` class, which is a custom exception for handling cases where a reservation's status cannot be updated in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class ReservationStatusUpdateNotAllowedException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public ReservationStatusUpdateNotAllowedException(Long id, String targetStatus) {
        super("Reservation with id " + id + " cannot be updated to status '" + targetStatus + "' due to its current status.");
    }
```
*Constructor that takes a reservation ID and a target status, and creates an exception message indicating the update is not allowed.*

```java
    public ReservationStatusUpdateNotAllowedException(String message) {
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
- **Purpose:** Thrown when a reservation's status cannot be updated due to its current state or business rules.
- **Constructors:**
  - By reservation ID and target status (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal that a reservation's status cannot be updated.
