# StaffMapper Documentation

This document provides a line-by-line explanation of the `StaffMapper` interface, which is responsible for mapping between `Staff` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.entity.Staff;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface StaffMapper {
```
*Defines the StaffMapper interface.*

```java
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Mapping Methods

```java
    @Mapping(target = "staffId", ignore = true)
    @Mapping(target = "role", ignore = true)
    Staff toEntity(StaffDTO dto);
```
*Maps a `StaffDTO` to a `Staff` entity, ignoring the `staffId` and `role` fields (to be set elsewhere, e.g., by the database or service layer).* 

```java
    StaffResponseDTO toResponseDTO(Staff entity);
```
*Maps a `Staff` entity to a `StaffResponseDTO` for API responses.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `Staff` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.
