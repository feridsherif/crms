# ReservationCancellationNotAllowedException Documentation

This document provides a line-by-line explanation of the `ReservationCancellationNotAllowedException` class, which is a custom exception for handling cases where a reservation cannot be cancelled in the CRMS system.

---

```java
package com.hilcoe.crms.exception;
```
*Declares the package for the exception class.*

```java
public class ReservationCancellationNotAllowedException extends RuntimeException {
```
*Defines a custom exception class that extends `RuntimeException`, making it an unchecked exception.*

```java
    private static final long serialVersionUID = 1L;
```
*Defines a unique version identifier for serialization. This is standard for all serializable classes.*

```java
    public ReservationCancellationNotAllowedException(Long id) {
        super("Reservation with id " + id + " cannot be cancelled because it is already COMPLETED.");
    }
```
*Constructor that takes a reservation ID and creates an exception message indicating the reservation cannot be cancelled because it is already completed.*

```java
    public ReservationCancellationNotAllowedException(String message) {
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
- **Purpose:** Thrown when a reservation cannot be cancelled due to its current status (e.g., already completed).
- **Constructors:**
  - By reservation ID (generates a standard message)
  - By custom message
- **Usage:** Used in service or repository layers to signal that a reservation cannot be cancelled.
