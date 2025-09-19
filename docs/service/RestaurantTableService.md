# RestaurantTableService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the RestaurantTableService.

```java
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.RestaurantTableDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.RestaurantTable;
import com.hilcoe.crms.exception.RestaurantTableNotFoundException;
import com.hilcoe.crms.mapper.RestaurantTableMapper;
import com.hilcoe.crms.repository.RestaurantTableRepository;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, exceptions, mappers, and repositories.

## Service Declaration
```java
@Service
public class RestaurantTableService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private RestaurantTableMapper restaurantTableMapper;
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
```
- Injects dependencies for mapping and table persistence.

### Create Table
```java
    public RestaurantTableDTO createTable(RestaurantTableDTO dto) {
        RestaurantTable table = restaurantTableMapper.toEntity(dto);
        table.setStatus(RestaurantTable.RestaurantTableStatus.AVAILABLE); // Always set to AVAILABLE on creation
        table = restaurantTableRepository.save(table);
        return restaurantTableMapper.toDTO(table);
    }
```
- Maps DTO to entity, sets status to AVAILABLE, saves, and returns the DTO.

### Delete Table
```java
    public void deleteTable(Long id) {
        if (!restaurantTableRepository.existsById(id)) {
            throw new RestaurantTableNotFoundException(id);
        }
        restaurantTableRepository.deleteById(id);
    }
```
- Checks if a table exists by ID, throws if not, otherwise deletes.

### Get All Tables
```java
    public List<RestaurantTableDTO> getAllTables() {
        return restaurantTableRepository.findAll().stream().map(restaurantTableMapper::toDTO)
                .collect(Collectors.toList());
    }
```
- Retrieves all tables, maps them to DTOs, and returns as a list.

### Get Paginated Tables
```java
    public PaginatedResponseDTO<RestaurantTableDTO> getPaginatedTables(int page, int size) {
        Page<RestaurantTable> tablePage = restaurantTableRepository.findAll(PageRequest.of(page, size));
        List<RestaurantTableDTO> dtos = tablePage.getContent().stream().map(restaurantTableMapper::toDTO)
                .collect(Collectors.toList());
        return new PaginatedResponseDTO<>(dtos, tablePage.getNumber(), tablePage.getSize(),
            // ...implementation continues...
        );
    }
```
- Retrieves paginated tables, maps them to DTOs, and prepares pagination info.

---

This file provides a detailed explanation of each section and method in the `RestaurantTableService` class, helping developers understand its structure and logic.
