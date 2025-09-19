# ShiftDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `ShiftDTO` class, which is used to transfer shift data between client and server (for shift creation and updates).

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation and date/time types.

---

## Class Declaration

```java
public class ShiftDTO {
```
- Declares the DTO class for shift data.

---

## Fields

```java
@NotNull(message = "branchId is required")
private Long branchId;
private LocalDateTime endTime;
@NotNull(message = "staffId is required")
private Long staffId;
private LocalDateTime startTime;
```
- `branchId`: The branch this shift is for (required).
- `endTime`: End time of the shift (optional).
- `staffId`: The staff member assigned to the shift (required).
- `startTime`: Start time of the shift (optional).

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for shift creation and update requests.
- Enforces validation for required fields.
- Contains all relevant info for a staff shift in the API.
