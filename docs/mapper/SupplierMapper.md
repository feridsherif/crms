# SupplierMapper Documentation

This document provides a line-by-line explanation of the `SupplierMapper` interface, which is responsible for mapping between `Supplier` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import com.hilcoe.crms.entity.Supplier;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface SupplierMapper {
```
*Defines the SupplierMapper interface.*

```java
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).*

---

### Mapping Methods

```java
    @Mapping(target = "supplierId", ignore = true)
    Supplier toEntity(SupplierDTO dto);
```
*Maps a `SupplierDTO` to a `Supplier` entity, ignoring the `supplierId` field (to be set elsewhere, e.g., by the database).* 

```java
    SupplierResponseDTO toResponseDTO(Supplier entity);
```
*Maps a `Supplier` entity to a `SupplierResponseDTO` for API responses.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `Supplier` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.
