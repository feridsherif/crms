package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.CategoryDTO;
import com.hilcoe.crms.dto.CategoryResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryDTO dto) {
        CategoryResponseDTO response = categoryService.createCategory(dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Category created", response));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(new ApiResponse<>("success", "Categories fetched", categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable Long id) {
        CategoryResponseDTO category = categoryService.getCategory(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Category fetched", category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO dto) {
        CategoryResponseDTO updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Category updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Category deleted", null));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getCategoriesPaginated(@RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<CategoryResponseDTO> paginated = categoryService.getCategoriesPaginated(PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponse<>("success", "Categories paginated fetched", paginated));
    }
}