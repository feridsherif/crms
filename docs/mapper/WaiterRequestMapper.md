# WaiterRequestMapper Documentation

This document provides a line-by-line explanation of the `WaiterRequestMapper` interface, which is responsible for mapping between `WaiterRequest` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.entity.WaiterRequest;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface WaiterRequestMapper {
```
*Defines the WaiterRequestMapper interface.*

```java
    WaiterRequestMapper INSTANCE = Mappers.getMapper(WaiterRequestMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).*

---

### Mapping Methods

```java
    @Mapping(target = "branchId", ignore = true)
    @Mapping(target = "handledBy", ignore = true)
    @Mapping(target = "requestId", ignore = true)
    @Mapping(target = "status", ignore = true)
    WaiterRequest toEntity(WaiterRequestDTO dto);
```
*Maps a `WaiterRequestDTO` to a `WaiterRequest` entity, ignoring fields that are set elsewhere (e.g., by the service or persistence layer).* 

```java
    WaiterRequestResponseDTO toResponseDTO(WaiterRequest entity);
```
*Maps a `WaiterRequest` entity to a `WaiterRequestResponseDTO` for API responses.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `WaiterRequest` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.
