# RoleService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the RoleService.

```java
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.dto.RoleResponseDTO;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.exception.RoleNotFoundException;
import com.hilcoe.crms.mapper.RoleMapper;
import com.hilcoe.crms.repository.PermissionRepository;
import com.hilcoe.crms.repository.RoleRepository;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, exceptions, mappers, and repositories.

## Service Declaration
```java
@Service
public class RoleService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleRepository roleRepository;
```
- Injects dependencies for audit logging, permission, mapping, and role persistence.

### Create Role
```java
    public RoleDTO createRole(RoleDTO dto, Long userId) {
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(dto.getPermissionIds()));
        Role role = new Role(null, dto.getName(), dto.getDescription());
        role.setPermissions(permissions);
        role = roleRepository.save(role);
        auditLogService.log(userId, "CREATE", "Role", role.getRoleId(), role);
        return toDTO(role);
    }
```
- Creates a new role, assigns permissions, saves, logs, and returns a DTO.

### Delete Role
```java
    public void deleteRole(Long id, Long userId) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
        auditLogService.log(userId, "DELETE", "Role", role.getRoleId(), role);
        roleRepository.deleteById(id);
    }
```
- Finds a role by ID, logs the deletion, and deletes it.

### Get All Roles
```java
    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toResponseDTO).collect(Collectors.toList());
    }
```
- Retrieves all roles, maps them to response DTOs, and returns as a list.

---

This file provides a detailed explanation of each section and method in the `RoleService` class, helping developers understand its structure and logic.
