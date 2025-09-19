# RoleMapper Documentation

This document provides a line-by-line explanation of the `RoleMapper` interface, which is responsible for mapping between `Role` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.dto.RoleResponseDTO;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.entity.Role;
```
*Imports Java collections, MapStruct annotations, and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface RoleMapper {
```
*Defines the RoleMapper interface.*

```java
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Mapping Methods

```java
    RoleDTO toDTO(Role entity);
```
*Maps a `Role` entity to a `RoleDTO`. All fields with matching names are mapped automatically.*

```java
    Role toEntity(RoleDTO dto);
```
*Maps a `RoleDTO` to a `Role` entity. All fields with matching names are mapped automatically.*

```java
    default RoleResponseDTO toResponseDTO(Role entity) {
        Set<Long> permissionIds = entity.getPermissions() == null ? null
            : entity.getPermissions().stream().map(Permission::getPermissionId).collect(Collectors.toSet());
        Set<String> permissionNames = entity.getPermissions() == null ? null
            : entity.getPermissions().stream().map(Permission::getName).collect(Collectors.toSet());
        return new RoleResponseDTO(entity.getRoleId(), entity.getName(), entity.getDescription(), permissionIds,
            permissionNames);
    }
```
*Custom method to map a `Role` entity to a `RoleResponseDTO`, extracting both permission IDs and names from the associated permissions.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `Role` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.

# RoleMapper.java - Line-by-Line Explanation

```java
package com.hilcoe.crms.mapper;
```
- Declares the package for the mapper.

```java
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.dto.RoleResponseDTO;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.entity.Role;
```
- Imports required classes for mapping, DTOs, entities, and Java collections.

```java
@Mapper(componentModel = "spring")
public interface RoleMapper {
```
- Declares this as a MapStruct mapper, to be managed by Spring.

```java
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
```
- Provides a static instance for manual usage (not needed with Spring, but sometimes used for tests).

```java
    RoleDTO toDTO(Role entity);
```
- Maps a Role entity to a RoleDTO.

```java
    Role toEntity(RoleDTO dto);
```
- Maps a RoleDTO to a Role entity.

```java
    default RoleResponseDTO toResponseDTO(Role entity) {
        Set<Long> permissionIds = entity.getPermissions() == null ? null
            : entity.getPermissions().stream().map(Permission::getPermissionId).collect(Collectors.toSet());
        Set<String> permissionNames = entity.getPermissions() == null ? null
            : entity.getPermissions().stream().map(Permission::getName).collect(Collectors.toSet());
        return new RoleResponseDTO(entity.getRoleId(), entity.getName(), entity.getDescription(), permissionIds,
            permissionNames);
    }
}
```
- Custom method to map a Role entity to a RoleResponseDTO, extracting permission IDs and names from the Role's permissions set.