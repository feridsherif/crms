package com.hilcoe.crms.service;

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

@Service
public class MenuService {
	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;

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

	public void deleteItem(Long id, Long userId) {
		MenuItem item = menuItemRepository.findById(id)
				.orElseThrow(() -> new com.hilcoe.crms.exception.MenuItemNotFoundException(id));
		auditLogService.log(userId, "DELETE", "MenuItem", item.getMenuItemId(), item);
		menuItemRepository.deleteById(id);
	}

	public List<MenuItemResponseDTO> getMenu() {
		return menuItemRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
	}

	public PaginatedResponseDTO<MenuItemResponseDTO> getMenuByCategoryPaginated(Long categoryId, Pageable pageable) {
		Page<MenuItem> page = menuItemRepository.findByCategory_CategoryId(categoryId, pageable);
		List<MenuItemResponseDTO> content = page.getContent().stream().map(this::toResponseDTO)
				.collect(Collectors.toList());
		int pageNum = page.getNumber();
		int pageSize = page.getSize();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		boolean hasNext = page.hasNext();
		boolean hasPrevious = page.hasPrevious();
		String sort = pageable.getSort().toString();
		Object filter = categoryId;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public MenuItemResponseDTO getMenuItemById(Long id) {
		MenuItem item = menuItemRepository.findById(id)
				.orElseThrow(() -> new com.hilcoe.crms.exception.MenuItemNotFoundException(id));
		return toResponseDTO(item);
	}

	public PaginatedResponseDTO<MenuItemResponseDTO> getMenuPaginated(Pageable pageable) {
		Page<MenuItem> page = menuItemRepository.findAll(pageable);
		List<MenuItemResponseDTO> content = page.getContent().stream().map(this::toResponseDTO)
				.collect(Collectors.toList());
		int pageNum = page.getNumber();
		int pageSize = page.getSize();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		boolean hasNext = page.hasNext();
		boolean hasPrevious = page.hasPrevious();
		String sort = pageable.getSort().toString();
		Object filter = null; // No filter logic for now
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null; // TODO: Generate if needed
		String previousPageUrl = null; // TODO: Generate if needed
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public PaginatedResponseDTO<MenuItemResponseDTO> getMenuPaginatedAdvanced(String name, Long categoryId,
			Boolean isAvailable, java.math.BigDecimal minPrice, java.math.BigDecimal maxPrice, int page, int size,
			String sortBy, String direction) {
		Sort sort = Sort.by(
				direction != null && direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
				(sortBy != null && !sortBy.isEmpty()) ? sortBy : "menuItemId");
		Pageable pageable = PageRequest.of(page, size, sort);
		var spec = MenuItemSpecification.filter(name, categoryId, isAvailable, minPrice, maxPrice);
		Page<MenuItem> result = menuItemRepository.findAll(spec, pageable);
		List<MenuItemResponseDTO> content = result.getContent().stream().map(this::toResponseDTO)
				.collect(Collectors.toList());
		int pageNum = result.getNumber();
		int pageSize = result.getSize();
		long totalElements = result.getTotalElements();
		int totalPages = result.getTotalPages();
		boolean hasNext = result.hasNext();
		boolean hasPrevious = result.hasPrevious();
		String sortStr = sort.toString();
		Object filter = null; // Optionally, build a filter summary
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	private MenuItemResponseDTO toResponseDTO(MenuItem item) {
		return new MenuItemResponseDTO(item.getMenuItemId(), item.getCategory().getCategoryId(), item.getName(),
				item.getDescription(), item.getPrice(), item.getIsAvailable());
	}

	public MenuItemResponseDTO updateItem(Long id, MenuItemDTO dto, Long userId) {
		MenuItem item = menuItemRepository.findById(id).orElseThrow(
				() -> new com.hilcoe.crms.exception.MenuItemNotFoundException("Menu item not found with id: " + id));
		MenuItem before = new MenuItem();
		before.setMenuItemId(item.getMenuItemId());
		before.setCategory(item.getCategory());
		before.setName(item.getName());
		before.setDescription(item.getDescription());
		before.setPrice(item.getPrice());
		before.setIsAvailable(item.getIsAvailable());
		Category category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> new com.hilcoe.crms.exception.MenuItemNotFoundException(
						"Category not found with id: " + dto.getCategoryId()));
		item.setCategory(category);
		item.setName(dto.getName());
		item.setDescription(dto.getDescription());
		item.setPrice(dto.getPrice());
		item.setIsAvailable(dto.getIsAvailable());
		MenuItem updated = menuItemRepository.save(item);
		HashMap<String, Object> data = new HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "MenuItem", updated.getMenuItemId(), data);
		return toResponseDTO(updated);
	}
}