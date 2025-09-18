package com.hilcoe.crms.service;

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

@Service
public class CategoryService {
	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryResponseDTO createCategory(CategoryDTO dto, Long userId) {
		Category category = new Category();
		category.setName(dto.name);
		category.setDescription(dto.description);
		Category saved = categoryRepository.save(category);
		auditLogService.log(userId, "CREATE", "Category", saved.getCategoryId(), saved);
		return toResponseDTO(saved);
	}

	public void deleteCategory(Long id, Long userId) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
		auditLogService.log(userId, "DELETE", "Category", category.getCategoryId(), category);
		categoryRepository.deleteById(id);
	}

	public List<CategoryResponseDTO> getAllCategories() {
		return categoryRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
	}

	public PaginatedResponseDTO<CategoryResponseDTO> getCategoriesPaginated(Pageable pageable) {
		Page<Category> page = categoryRepository.findAll(pageable);
		List<CategoryResponseDTO> content = page.getContent().stream().map(this::toResponseDTO)
				.collect(Collectors.toList());
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
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public PaginatedResponseDTO<CategoryResponseDTO> getCategoriesPaginatedAdvanced(String name, String description,
			int page, int size, String sortBy, String direction) {
		org.springframework.data.domain.Sort sort = direction.equalsIgnoreCase("desc")
				? org.springframework.data.domain.Sort.by(sortBy).descending()
				: org.springframework.data.domain.Sort.by(sortBy).ascending();
		org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size,
				sort);
		org.springframework.data.domain.Page<Category> categoryPage = categoryRepository.findAll(pageable);
		java.util.List<Category> filtered = categoryPage.getContent().stream().filter(
				c -> name == null || (c.getName() != null && c.getName().toLowerCase().contains(name.toLowerCase())))
				.filter(c -> description == null || (c.getDescription() != null
						&& c.getDescription().toLowerCase().contains(description.toLowerCase())))
				.collect(java.util.stream.Collectors.toList());
		java.util.List<CategoryResponseDTO> content = filtered.stream().map(this::toResponseDTO)
				.collect(java.util.stream.Collectors.toList());
		int pageNum = categoryPage.getNumber();
		int pageSize = categoryPage.getSize();
		long totalElements = categoryPage.getTotalElements();
		int totalPages = categoryPage.getTotalPages();
		boolean hasNext = categoryPage.hasNext();
		boolean hasPrevious = categoryPage.hasPrevious();
		String sortStr = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public CategoryResponseDTO getCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
		return toResponseDTO(category);
	}

	private CategoryResponseDTO toResponseDTO(Category category) {
		CategoryResponseDTO dto = new CategoryResponseDTO();
		dto.categoryId = category.getCategoryId();
		dto.name = category.getName();
		dto.description = category.getDescription();
		return dto;
	}

	public CategoryResponseDTO updateCategory(Long id, CategoryDTO dto, Long userId) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
		Category before = new Category();
		before.setCategoryId(category.getCategoryId());
		before.setName(category.getName());
		before.setDescription(category.getDescription());
		category.setName(dto.name);
		category.setDescription(dto.description);
		Category updated = categoryRepository.save(category);
		java.util.HashMap<String, Object> data = new java.util.HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Category", updated.getCategoryId(), data);
		return toResponseDTO(updated);
	}
}