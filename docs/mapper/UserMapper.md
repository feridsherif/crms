# UserMapper Documentation

This document provides a line-by-line explanation of the `UserMapper` interface, which is responsible for mapping between `User` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import com.hilcoe.crms.dto.UserDTO;
import com.hilcoe.crms.dto.UserResponseDTO;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.User;
```
- Imports for MapStruct, DTOs, entities, and Java collections.

```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
```
- Declares a MapStruct mapper for Spring.

### Custom mapping methods

```java
    @Named("mapIdsToRoles")
    default Set<Role> mapIdsToRoles(Set<Long> roleIds) {
        if (roleIds == null) {
            return null;
        }
        return roleIds.stream().map(id -> {
            Role role = new Role();
            role.setRoleId(id);
            return role;
        }).collect(Collectors.toSet());
    }
```
- Converts a set of role IDs to a set of Role entities.

```java
    @Named("mapRolesToIds")
    default Set<Long> mapRolesToIds(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream().map(Role::getRoleId).collect(Collectors.toSet());
    }
```
- Converts a set of Role entities to a set of role IDs.

### Mapping methods

```java
    @Mapping(source = "roles", target = "roleIds", qualifiedByName = "mapRolesToIds")
    UserDTO toDTO(User entity);
```
- Maps User entity to UserDTO, converting roles to roleIds.

```java
    @Mapping(source = "roleIds", target = "roles", qualifiedByName = "mapIdsToRoles")
    @Mapping(target = "userId", ignore = true)
    User toEntity(UserDTO dto);
```
- Maps UserDTO to User entity, converting roleIds to roles and ignoring userId.

```java
    @Mapping(target = "roleIds", source = "roles", qualifiedByName = "mapRolesToIds")
    UserResponseDTO toResponseDTO(User entity);
}
```
- Maps User entity to UserResponseDTO, converting roles to roleIds.

---

## Summary
- **Purpose:** Converts between `User` entity and its DTOs for service and controller layers, handling role relationships as sets of IDs.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.