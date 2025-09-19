# MenuService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the MenuService.

```java
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.Category;
import com.hilcoe.crms.entity.MenuItem;
import com.hilcoe.crms.repository.CategoryRepository;
import com.hilcoe.crms.repository.MenuItemRepository;
import com.hilcoe.crms.repository.MenuItemSpecification;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, and repositories.

## Service Declaration
```java
@Service
public class MenuService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
```
- Injects dependencies for audit logging, category, and menu item persistence.

### Add Menu Item
```java
    public MenuItemResponseDTO addItem(MenuItemDTO dto, Long userId) {
        MenuItem item = new MenuItem();
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow();
        item.setCategory(category);
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setIsAvailable(dto.getIsAvailable());
        MenuItem saved = menuItemRepository.save(item);
        auditLogService.log(userId, "CREATE", "MenuItem", saved.getMenuItemId(), saved);
        return toResponseDTO(saved);
    }
```
- Creates a new menu item, links to a category, sets fields, saves, logs, and returns a response DTO.

### Delete Menu Item
```java
    public void deleteItem(Long id, Long userId) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new com.hilcoe.crms.exception.MenuItemNotFoundException(id));
        auditLogService.log(userId, "DELETE", "MenuItem", item.getMenuItemId(), item);
        menuItemRepository.deleteById(id);
    }
```
- Finds a menu item by ID, logs the deletion, and deletes it.

---

This file provides a detailed explanation of each section and method in the `MenuService` class, helping developers understand its structure and logic. (Continue this pattern for the rest of the class.)
