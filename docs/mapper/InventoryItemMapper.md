# InventoryItemMapper Documentation

This document provides a line-by-line explanation of the `InventoryItemMapper` interface, which is responsible for mapping between `InventoryItem` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import com.hilcoe.crms.entity.InventoryItem;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface InventoryItemMapper {
```
*Defines the InventoryItemMapper interface.*

```java
    InventoryItemMapper INSTANCE = Mappers.getMapper(InventoryItemMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Mapping Methods

```java
    InventoryItemDTO toDTO(InventoryItem entity);
```
*Maps an `InventoryItem` entity to an `InventoryItemDTO`. All fields with matching names are mapped automatically.*

```java
    @Mapping(target = "inventoryItemId", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    InventoryItem toEntity(InventoryItemDTO dto);
```
*Maps an `InventoryItemDTO` to an `InventoryItem` entity, ignoring the `inventoryItemId` and `supplier` fields (to be set elsewhere, e.g., in the service layer).* 

```java
    InventoryItemResponseDTO toResponseDTO(InventoryItem entity);
```
*Maps an `InventoryItem` entity to an `InventoryItemResponseDTO` for API responses.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `InventoryItem` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.

# InventoryItemMapper.java - Line-by-Line Explanation

```java
package com.hilcoe.crms.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import com.hilcoe.crms.entity.InventoryItem;
```
- Imports for MapStruct, DTOs, and entity.

```java
@Mapper(componentModel = "spring")
public interface InventoryItemMapper {
    InventoryItemMapper INSTANCE = Mappers.getMapper(InventoryItemMapper.class);
```
- Declares a MapStruct mapper for Spring.

```java
    InventoryItemDTO toDTO(InventoryItem entity);
```
- Maps InventoryItem entity to DTO.

```java
    @Mapping(target = "inventoryItemId", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    InventoryItem toEntity(InventoryItemDTO dto);
```
- Maps DTO to entity, ignoring the ID and supplier (to be set elsewhere).

```java
    InventoryItemResponseDTO toResponseDTO(InventoryItem entity);
}
```
- Maps entity to a response DTO.