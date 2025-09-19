# PermissionService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the service.

```java
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.PermissionDTO;
import com.hilcoe.crms.dto.PermissionResponseDTO;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.exception.PermissionNotFoundException;
import com.hilcoe.crms.mapper.PermissionMapper;
import com.hilcoe.crms.repository.PermissionRepository;
```
- Imports required classes for dependency injection, data transfer, paging, mapping, and exception handling.

## Service Declaration
```java
@Service
public class PermissionService {
```
- Marks the class as a Spring service component.

### Dependencies
```java
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionRepository permissionRepository;
```
- Injects the PermissionMapper and PermissionRepository beans for mapping and database operations.

### Create Permission
```java
    public PermissionDTO createPermission(PermissionDTO dto) {
        Permission permission = new Permission(null, dto.getName(), dto.getDescription());
        permission = permissionRepository.save(permission);
        return toDTO(permission);
    }
```
- Creates a new Permission entity from the DTO, saves it, and returns the saved entity as a DTO.

### Delete Permission
```java
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new PermissionNotFoundException(id);
        }
        permissionRepository.deleteById(id);
    }
```
- Checks if a permission exists by ID, throws an exception if not, otherwise deletes it.

### Get All Permissions
```java
    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
```
- Retrieves all permissions, maps them to DTOs, and returns as a list.

### Get Permission by ID
```java
    public PermissionDTO getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
        return toDTO(permission);
    }
```
- Finds a permission by ID, throws if not found, and returns as a DTO.

### Get Permissions (Paginated)
```java
    public Page<PermissionResponseDTO> getPermissions(Pageable pageable) {
        // ...implementation...
    }
```
- Returns a paginated list of permissions as PermissionResponseDTOs.

---

This file provides a detailed explanation of each section and method in the `PermissionService` class, helping developers understand its structure and logic.