# ShiftMapper Documentation

This document provides a line-by-line explanation of the `ShiftMapper` utility class, which is responsible for mapping between `Shift` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import com.hilcoe.crms.dto.ShiftDTO;
import com.hilcoe.crms.dto.ShiftResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.Shift;
import com.hilcoe.crms.entity.Staff;
```
*Imports relevant DTO and entity classes.*

---

```java
public class ShiftMapper {
```
*Defines the ShiftMapper utility class (not an interface, not using MapStruct annotations).* 

---

### Static Mapping Methods

```java
    public static Shift toEntity(ShiftDTO dto, Staff staff, Branch branch) {
        if (dto == null) {
            return null;
        }
        Shift shift = new Shift();
        shift.setStaff(staff);
        shift.setBranch(branch);
        shift.setStartTime(dto.getStartTime());
        shift.setEndTime(dto.getEndTime());
        return shift;
    }
```
*Creates a new `Shift` entity from a `ShiftDTO`, setting the staff, branch, start time, and end time. Returns null if the DTO is null.*

```java
    public static ShiftResponseDTO toResponseDTO(Shift shift) {
        if (shift == null) {
            return null;
        }
        ShiftResponseDTO dto = new ShiftResponseDTO();
        dto.setShiftId(shift.getShiftId());
        dto.setStaffId(shift.getStaff() != null ? shift.getStaff().getStaffId() : null);
        dto.setBranchId(shift.getBranch() != null ? shift.getBranch().getBranchId() : null);
        dto.setStartTime(shift.getStartTime());
        dto.setEndTime(shift.getEndTime());
        return dto;
    }
```
*Creates a `ShiftResponseDTO` from a `Shift` entity, extracting IDs and times. Returns null if the entity is null.*

```java
    public static void updateEntity(Shift shift, ShiftDTO dto, Staff staff, Branch branch) {
        if (staff != null) {
            shift.setStaff(staff);
        }
        if (branch != null) {
            shift.setBranch(branch);
        }
        if (dto.getStartTime() != null) {
            shift.setStartTime(dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            shift.setEndTime(dto.getEndTime());
        }
    }
```
*Updates an existing `Shift` entity with values from a `ShiftDTO`, and optionally new staff/branch. Only non-null values are updated.*

```
}
```
*End of the class definition.*

---

## Summary
- **Purpose:** Converts between `Shift` entity and its DTOs for service and controller layers.
- **Framework:** Plain Java utility class (not MapStruct).
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.

# ShiftMapper.java - Line-by-Line Explanation

```java
package com.hilcoe.crms.mapper;
```
- Declares the package.

```java
import com.hilcoe.crms.dto.ShiftDTO;
import com.hilcoe.crms.dto.ShiftResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.Shift;
import com.hilcoe.crms.entity.Staff;
```
- Imports DTOs and entities needed for mapping.

```java
public class ShiftMapper {
```
- Declares a utility class (not an interface, not using MapStruct).

### toEntity

```java
    public static Shift toEntity(ShiftDTO dto, Staff staff, Branch branch) {
        if (dto == null) {
            return null;
        }
        Shift shift = new Shift();
        shift.setStaff(staff);
        shift.setBranch(branch);
        shift.setStartTime(dto.getStartTime());
        shift.setEndTime(dto.getEndTime());
        return shift;
    }
```
- Converts a ShiftDTO and related Staff/Branch into a Shift entity.

### toResponseDTO

```java
    public static ShiftResponseDTO toResponseDTO(Shift shift) {
        if (shift == null) {
            return null;
        }
        ShiftResponseDTO dto = new ShiftResponseDTO();
        dto.setShiftId(shift.getShiftId());
        dto.setStaffId(shift.getStaff() != null ? shift.getStaff().getStaffId() : null);
        dto.setBranchId(shift.getBranch() != null ? shift.getBranch().getBranchId() : null);
        dto.setStartTime(shift.getStartTime());
        dto.setEndTime(shift.getEndTime());
        return dto;
    }
```
- Converts a Shift entity to a ShiftResponseDTO, extracting IDs from related entities.

### updateEntity

```java
    public static void updateEntity(Shift shift, ShiftDTO dto, Staff staff, Branch branch) {
        if (staff != null) {
            shift.setStaff(staff);
        }
        if (branch != null) {
            shift.setBranch(branch);
        }
        if (dto.getStartTime() != null) {
            shift.setStartTime(dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            shift.setEndTime(dto.getEndTime());
        }
    }
}
```
- Updates an existing Shift entity with values from a ShiftDTO and optionally new Staff/Branch.