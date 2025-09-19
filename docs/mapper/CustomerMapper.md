# CustomerMapper Documentation

This document provides a line-by-line explanation of the `CustomerMapper` interface, which is responsible for mapping between `Customer` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.CustomerDTO;
import com.hilcoe.crms.entity.Customer;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface CustomerMapper {
```
*Defines the CustomerMapper interface.*

```java
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Mapping Methods

```java
    @Mapping(target = "userId", source = "user.userId")
    CustomerDTO toDTO(Customer entity);
```
*Maps a `Customer` entity to a `CustomerDTO`, extracting the `userId` from the nested `User` object. Other fields are mapped automatically.*

```java
    @Mapping(target = "user", ignore = true)
    Customer toEntity(CustomerDTO dto);
```
*Maps a `CustomerDTO` to a `Customer` entity, ignoring the `user` field (to be set elsewhere, e.g., in the service layer).* 

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `Customer` entity and its DTO for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.

# CustomerMapper.java - Line-by-Line Explanation

```java
package com.hilcoe.crms.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.hilcoe.crms.dto.CustomerDTO;
import com.hilcoe.crms.entity.Customer;
```
- Imports for MapStruct, DTO, and entity.

```java
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
```
- Declares a MapStruct mapper for Spring.

```java
    @Mapping(target = "userId", source = "user.userId")
    // name and phone are mapped automatically by MapStruct
    CustomerDTO toDTO(Customer entity);
```
- Maps Customer entity to DTO, extracting userId from the related User entity.

```java
    @Mapping(target = "user", ignore = true)
    // name and phone are mapped automatically by MapStruct
    Customer toEntity(CustomerDTO dto);
}
```
- Maps DTO to entity, ignoring the user (to be set elsewhere).