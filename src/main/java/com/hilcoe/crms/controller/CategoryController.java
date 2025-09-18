package com.hilcoe.crms.controller;

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

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping
	@PreAuthorize("hasAuthority('CATEGORY_CREATE')")
	public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryDTO dto, Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		CategoryResponseDTO response = categoryService.createCategory(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Category created", response));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('CATEGORY_DELETE')")
	public ResponseEntity<Object> deleteCategory(@PathVariable Long id, Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		categoryService.deleteCategory(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Category deleted", null));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('CATEGORY_READ')")
	public ResponseEntity<Object> getAllCategories() {
		List<CategoryResponseDTO> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(ApiResponse.success("Categories fetched", categories));
	}

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

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('CATEGORY_READ')")
	public ResponseEntity<Object> getCategory(@PathVariable Long id) {
		CategoryResponseDTO category = categoryService.getCategory(id);
		return ResponseEntity.ok(ApiResponse.success("Category fetched", category));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('CATEGORY_UPDATE')")
	public ResponseEntity<Object> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO dto,
			Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		CategoryResponseDTO updated = categoryService.updateCategory(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Category updated", updated));
	}
}