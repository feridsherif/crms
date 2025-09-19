# PermissionMapper Documentation

This document provides a line-by-line explanation of the `PermissionMapper` interface, which is responsible for mapping between `Permission` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.PermissionDTO;
import com.hilcoe.crms.dto.PermissionResponseDTO;
import com.hilcoe.crms.entity.Permission;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface PermissionMapper {
```
*Defines the PermissionMapper interface.*

```java
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Mapping Methods

```java
    PermissionDTO toDTO(Permission entity);
```
*Maps a `Permission` entity to a `PermissionDTO`. All fields with matching names are mapped automatically.*

```java
    @Mapping(target = "permissionId", ignore = true)
    Permission toEntity(PermissionDTO dto);
```
*Maps a `PermissionDTO` to a `Permission` entity, ignoring the `permissionId` field (to be set elsewhere, e.g., by the database).* 

```java
    PermissionResponseDTO toResponseDTO(Permission entity);
```
*Maps a `Permission` entity to a `PermissionResponseDTO` for API responses.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `Permission` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.
