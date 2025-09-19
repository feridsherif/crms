# ShiftResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `ShiftResponseDTO` class, which is used to transfer shift data from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.time.LocalDateTime;
```
- Declares the package and imports the date/time type.

---

## Class Declaration

```java
public class ShiftResponseDTO {
```
- Declares the DTO class for shift response data.

---

## Fields

```java
private Long branchId;
private LocalDateTime endTime;
private Long shiftId;
private Long staffId;
private LocalDateTime startTime;
```
- `branchId`: The branch this shift is for.
- `endTime`: End time of the shift.
- `shiftId`: Unique identifier for the shift.
- `staffId`: The staff member assigned to the shift.
- `startTime`: Start time of the shift.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending shift data in API responses.
- Contains all relevant info for a staff shift, including IDs and times.
