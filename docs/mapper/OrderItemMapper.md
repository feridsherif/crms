# OrderItemMapper Documentation

This document provides a line-by-line explanation of the `OrderItemMapper` interface, which is responsible for mapping between `OrderItem` entities and their corresponding DTOs in the CRMS system.

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

import com.hilcoe.crms.dto.OrderItemDTO;
import com.hilcoe.crms.dto.OrderItemResponseDTO;
import com.hilcoe.crms.entity.MenuItem;
import com.hilcoe.crms.entity.OrderItem;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface OrderItemMapper {
```
*Defines the OrderItemMapper interface.*

```java
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Custom Mapping Methods

```java
    @Named("mapIdToMenuItem")
    default MenuItem mapIdToMenuItem(Long menuItemId) {
        if (menuItemId == null) {
            return null;
        }
        MenuItem item = new MenuItem();
        item.setMenuItemId(menuItemId);
        return item;
    }
```
*Custom method to convert a menuItemId to a MenuItem entity. Used for mapping DTOs to entities.*

```java
    default Long mapMenuItemToId(MenuItem menuItem) {
        return menuItem != null ? menuItem.getMenuItemId() : null;
    }
```
*Custom method to extract the menuItemId from a MenuItem entity. Used for mapping entities to DTOs.*

---

### Mapping Methods

```java
    @Mapping(source = "menuItem.menuItemId", target = "menuItemId")
    OrderItemDTO toDTO(OrderItem entity);
```
*Maps an `OrderItem` entity to an `OrderItemDTO`, extracting the menuItemId from the nested MenuItem object.*

```java
    @Mapping(source = "menuItemId", target = "menuItem", qualifiedByName = "mapIdToMenuItem")
    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    OrderItem toEntity(OrderItemDTO dto);
```
*Maps an `OrderItemDTO` to an `OrderItem` entity, using the custom method to convert menuItemId to a MenuItem. Ignores orderItemId, order, and unitPrice fields (to be set elsewhere).* 

```java
    @Mapping(source = "menuItem.menuItemId", target = "menuItemId")
    OrderItemResponseDTO toResponseDTO(OrderItem entity);
```
*Maps an `OrderItem` entity to an `OrderItemResponseDTO` for API responses, extracting the menuItemId from the nested MenuItem object.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `OrderItem` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.
