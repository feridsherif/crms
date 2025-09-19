# CategoryController.java - Line-by-Line Explanation

This document explains the `CategoryController` class, which is a Spring Boot REST controller for managing menu categories in the CRMS system. It provides endpoints for creating, reading, updating, and deleting categories, as well as paginated and filtered retrieval. Each method is annotated for security and HTTP mapping.

---

## Class and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.CategoryDTO;
import com.hilcoe.crms.dto.CategoryResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.CategoryService;

import jakarta.validation.Valid;
```
- Imports required Java and Spring classes for REST, security, validation, and DTOs.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/categories")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the `CategoryService` dependency for business logic.

---

## Endpoint: Create Category

```java
	@PostMapping
	@PreAuthorize("hasAuthority('CATEGORY_CREATE')")
	public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryDTO dto, Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		CategoryResponseDTO response = categoryService.createCategory(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Category created", response));
	}
```
- `@PostMapping`: Handles POST requests to `/api/v1/categories`.
- `@PreAuthorize(...)`: Only users with `CATEGORY_CREATE` authority can access.
- `@Valid @RequestBody CategoryDTO dto`: Validates and binds the request body to a DTO.
- `Authentication authentication`: Injects the current user's authentication.
- Extracts user ID from authentication, calls service to create category, and returns a 201 response with the result.

---

## Endpoint: Delete Category

```java
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('CATEGORY_DELETE')")
	public ResponseEntity<Object> deleteCategory(@PathVariable Long id, Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		categoryService.deleteCategory(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Category deleted", null));
	}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/categories/{id}`.
- `@PreAuthorize(...)`: Requires `CATEGORY_DELETE` authority.
- `@PathVariable Long id`: Binds the category ID from the URL.
- Calls service to delete the category and returns a success response.

---

## Endpoint: Get All Categories

```java
	@GetMapping
	@PreAuthorize("hasAuthority('CATEGORY_READ')")
	public ResponseEntity<Object> getAllCategories() {
		List<CategoryResponseDTO> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(ApiResponse.success("Categories fetched", categories));
	}
```
- `@GetMapping`: Handles GET requests to `/api/v1/categories`.
- `@PreAuthorize(...)`: Requires `CATEGORY_READ` authority.
- Calls service to fetch all categories and returns them in the response.

---

## Endpoint: Get Categories (Paginated & Filtered)

```java
	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('CATEGORY_READ')")
	public ResponseEntity<Object> getCategoriesPaginated(@RequestParam(required = false) String name,
			@RequestParam(required = false) String description, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "categoryId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<CategoryResponseDTO> paginated = categoryService.getCategoriesPaginatedAdvanced(name,
				description, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Categories paginated fetched", paginated));
	}
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/categories/paginated`.
- `@PreAuthorize(...)`: Requires `CATEGORY_READ` authority.
- `@RequestParam`: Accepts optional query parameters for filtering, pagination, and sorting.
- Calls service for paginated and filtered results, returns them in the response.

---

## Endpoint: Get Category by ID

```java
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('CATEGORY_READ')")
	public ResponseEntity<Object> getCategory(@PathVariable Long id) {
		CategoryResponseDTO category = categoryService.getCategory(id);
		return ResponseEntity.ok(ApiResponse.success("Category fetched", category));
	}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/categories/{id}`.
- `@PreAuthorize(...)`: Requires `CATEGORY_READ` authority.
- `@PathVariable Long id`: Binds the category ID from the URL.
- Calls service to fetch the category by ID and returns it.

---

## Endpoint: Update Category

```java
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
	public ResponseEntity<Object> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO dto,
			Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		CategoryResponseDTO updated = categoryService.updateCategory(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Category updated", updated));
	}
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/categories/{id}`.
- `@PreAuthorize(...)`: Requires `CATEGORY_UPDATE` authority.
- `@PathVariable Long id`: Binds the category ID from the URL.
- `@Valid @RequestBody CategoryDTO dto`: Validates and binds the request body.
- `Authentication authentication`: Injects the current user's authentication.
- Calls service to update the category and returns the updated result.

---

## Summary

- This controller provides a RESTful API for managing menu categories.
- Security is enforced via method-level `@PreAuthorize` annotations.
- Supports CRUD operations and advanced paginated queries.
- Uses DTOs for request and response payloads.
- Relies on a service layer for business logic.
