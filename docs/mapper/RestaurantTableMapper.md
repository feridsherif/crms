# RestaurantTableMapper Documentation

This document provides a line-by-line explanation of the `RestaurantTableMapper` interface, which is responsible for mapping between `RestaurantTable` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.RestaurantTableDTO;
import com.hilcoe.crms.dto.RestaurantTableResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.RestaurantTable;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface RestaurantTableMapper {
```
*Defines the RestaurantTableMapper interface.*

```java
    RestaurantTableMapper INSTANCE = Mappers.getMapper(RestaurantTableMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Custom Mapping Methods

```java
    @Named("branchIdToBranch")
    default Branch branchIdToBranch(Long branchId) {
        if (branchId == null) {
            return null;
        }
        Branch branch = new Branch();
        branch.setBranchId(branchId);
        return branch;
    }
```
*Custom method to convert a branchId to a Branch entity. Used for mapping DTOs to entities.*

---

### Mapping Methods

```java
    RestaurantTableDTO toDTO(RestaurantTable entity);
```
*Maps a `RestaurantTable` entity to a `RestaurantTableDTO`. All fields with matching names are mapped automatically.*

```java
    @Mapping(target = "branch", source = "branchId", qualifiedByName = "branchIdToBranch")
    @Mapping(target = "tableId", ignore = true)
    RestaurantTable toEntity(RestaurantTableDTO dto);
```
*Maps a `RestaurantTableDTO` to a `RestaurantTable` entity, using the custom method to convert branchId to a Branch. Ignores tableId (to be set elsewhere).* 

```java
    @Mapping(target = "branchId", source = "branch.branchId")
    RestaurantTableResponseDTO toResponseDTO(RestaurantTable entity);
```
*Maps a `RestaurantTable` entity to a `RestaurantTableResponseDTO` for API responses, extracting the branchId from the nested Branch object.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `RestaurantTable` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.
