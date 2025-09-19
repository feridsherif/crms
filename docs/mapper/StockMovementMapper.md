# StockMovementMapper Documentation

This document provides a line-by-line explanation of the `StockMovementMapper` interface, which is responsible for mapping between `StockMovement` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.StockMovementDTO;
import com.hilcoe.crms.entity.InventoryItem;
import com.hilcoe.crms.entity.StockMovement;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring", uses = { InventoryItemMapper.class })
```
*Indicates this interface is a MapStruct mapper, managed as a Spring bean, and uses InventoryItemMapper for nested mapping.*

```java
public interface StockMovementMapper {
```
*Defines the StockMovementMapper interface.*

```java
    StockMovementMapper INSTANCE = Mappers.getMapper(StockMovementMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Custom Mapping Methods

```java
    default InventoryItem mapIdToInventoryItem(Long inventoryItemId) {
        if (inventoryItemId == null) {
            return null;
        }
        InventoryItem item = new InventoryItem();
        item.setInventoryItemId(inventoryItemId);
        return item;
    }
```
*Custom method to convert an inventoryItemId to an InventoryItem entity. Used for mapping DTOs to entities.*

```java
    default Long mapInventoryItemToId(InventoryItem inventoryItem) {
        return inventoryItem != null ? inventoryItem.getInventoryItemId() : null;
    }
```
*Custom method to extract the inventoryItemId from an InventoryItem entity. Used for mapping entities to DTOs.*

---

### Mapping Methods

```java
    @Mapping(source = "inventoryItem.inventoryItemId", target = "inventoryItemId")
    @Mapping(source = "movementId", target = "movementId")
    StockMovementDTO toDTO(StockMovement entity);
```
*Maps a `StockMovement` entity to a `StockMovementDTO`, extracting the inventoryItemId from the nested InventoryItem object.*

```java
    @Mapping(source = "inventoryItemId", target = "inventoryItem.inventoryItemId")
    @Mapping(source = "movementId", target = "movementId")
    StockMovement toEntity(StockMovementDTO dto);
```
*Maps a `StockMovementDTO` to a `StockMovement` entity, using the custom method to convert inventoryItemId to an InventoryItem.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `StockMovement` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.
