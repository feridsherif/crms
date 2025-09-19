# OrderMapper Documentation

This document provides a line-by-line explanation of the `OrderMapper` interface, which is responsible for mapping between `Order` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.OrderCreateDTO;
import com.hilcoe.crms.dto.OrderFullResponseDTO;
import com.hilcoe.crms.dto.OrderResponseDTO;
import com.hilcoe.crms.entity.Order;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface OrderMapper {
```
*Defines the OrderMapper interface.*

```java
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Mapping Methods

```java
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "staffId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(OrderCreateDTO dto);
```
*Maps an `OrderCreateDTO` to an `Order` entity, ignoring fields that are set elsewhere (e.g., by the service or persistence layer).* 

```java
    OrderFullResponseDTO toFullResponseDTO(Order entity);
```
*Maps an `Order` entity to an `OrderFullResponseDTO` for detailed API responses.*

```java
    OrderResponseDTO toResponseDTO(Order entity);
```
*Maps an `Order` entity to an `OrderResponseDTO` for summary API responses.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `Order` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.
