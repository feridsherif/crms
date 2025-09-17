package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.CategoryDTO;
import com.hilcoe.crms.dto.CategoryResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.Category;
import com.hilcoe.crms.exception.CategoryNotFoundException;
import com.hilcoe.crms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDTO createCategory(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.name);
        category.setDescription(dto.description);
        Category saved = categoryRepository.save(category);
        return toResponseDTO(saved);
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public CategoryResponseDTO getCategory(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        return toResponseDTO(category);
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryDTO dto) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        category.setName(dto.name);
        category.setDescription(dto.description);
        Category updated = categoryRepository.save(category);
        return toResponseDTO(updated);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public PaginatedResponseDTO<CategoryResponseDTO> getCategoriesPaginated(Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(pageable);
        List<CategoryResponseDTO> content = page.getContent().stream().map(this::toResponseDTO).collect(Collectors.toList());
        int pageNum = page.getNumber();
        int pageSize = page.getSize();
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();
        String sort = pageable.getSort().toString();
        Object filter = null;
        long firstElementIndex = pageNum * pageSize + 1;
        long lastElementIndex = firstElementIndex + content.size() - 1;
        String nextPageUrl = null;
        String previousPageUrl = null;
        return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages,
            hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

    private CategoryResponseDTO toResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.categoryId = category.getCategoryId();
        dto.name = category.getName();
        dto.description = category.getDescription();
        return dto;
    }
}