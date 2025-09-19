# MenuItemMapper Documentation

This document provides a line-by-line explanation of the `MenuItemMapper` interface, which is responsible for mapping between `MenuItem` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import com.hilcoe.crms.entity.MenuItem;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface MenuItemMapper {
```
*Defines the MenuItemMapper interface.*

```java
    MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Mapping Methods

```java
    MenuItemDTO toDTO(MenuItem entity);
```
*Maps a `MenuItem` entity to a `MenuItemDTO`. All fields with matching names are mapped automatically.*

```java
    @Mapping(target = "menuItemId", ignore = true)
    @Mapping(target = "category", ignore = true)
    MenuItem toEntity(MenuItemDTO dto);
```
*Maps a `MenuItemDTO` to a `MenuItem` entity, ignoring the `menuItemId` and `category` fields (to be set elsewhere, e.g., in the service layer).* 

```java
    MenuItemResponseDTO toResponseDTO(MenuItem entity);
```
*Maps a `MenuItem` entity to a `MenuItemResponseDTO` for API responses.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `MenuItem` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.

# MenuItemMapper.java - Line-by-Line Explanation

```java
package com.hilcoe.crms.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import com.hilcoe.crms.entity.MenuItem;
```
- Imports for MapStruct, DTOs, and entity.

```java
@Mapper(componentModel = "spring")
public interface MenuItemMapper {
    MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);
```
- Declares a MapStruct mapper for Spring.

```java
    MenuItemDTO toDTO(MenuItem entity);
```
- Maps MenuItem entity to DTO.

```java
    @Mapping(target = "menuItemId", ignore = true)
    @Mapping(target = "category", ignore = true)
    MenuItem toEntity(MenuItemDTO dto);
```
- Maps DTO to entity, ignoring the ID and category (to be set elsewhere).

```java
    MenuItemResponseDTO toResponseDTO(MenuItem entity);
}
```
- Maps entity to a response DTO.