# CategoryService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the CategoryService.

```java
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.CategoryDTO;
import com.hilcoe.crms.dto.CategoryResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.Category;
import com.hilcoe.crms.exception.CategoryNotFoundException;
import com.hilcoe.crms.repository.CategoryRepository;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, exceptions, and repositories.

## Service Declaration
```java
@Service
public class CategoryService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private CategoryRepository categoryRepository;
```
- Injects dependencies for audit logging and category persistence.

### Create Category
```java
    public CategoryResponseDTO createCategory(CategoryDTO dto, Long userId) {
        Category category = new Category();
        category.setName(dto.name);
        category.setDescription(dto.description);
        Category saved = categoryRepository.save(category);
        auditLogService.log(userId, "CREATE", "Category", saved.getCategoryId(), saved);
        return toResponseDTO(saved);
    }
```
- Creates a new category, saves it, logs the action, and returns a response DTO.

### Delete Category
```java
    public void deleteCategory(Long id, Long userId) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        auditLogService.log(userId, "DELETE", "Category", category.getCategoryId(), category);
        categoryRepository.deleteById(id);
    }
```
- Finds a category by ID, logs the deletion, and deletes it.

### Get All Categories
```java
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
```
- Retrieves all categories, maps them to response DTOs, and returns as a list.

### Get Categories (Paginated)
```java
    public PaginatedResponseDTO<CategoryResponseDTO> getCategoriesPaginated(Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(pageable);
        List<CategoryResponseDTO> content = page.getContent().stream().map(this::toResponseDTO)
                .collect(Collectors.toList());
        int pageNum = page.getNumber();
```
- Retrieves paginated categories, maps them to response DTOs, and prepares pagination info.

---

This file provides a detailed explanation of each section and method in the `CategoryService` class, helping developers understand its structure and logic. (Continue this pattern for the rest of the class.)
