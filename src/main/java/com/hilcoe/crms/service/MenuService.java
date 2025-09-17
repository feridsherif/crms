package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import com.hilcoe.crms.entity.MenuItem;
import com.hilcoe.crms.entity.Category;
import com.hilcoe.crms.repository.MenuItemRepository;
import com.hilcoe.crms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.AuditLogService;
import java.util.HashMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AuditLogService auditLogService;

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

	public MenuItemResponseDTO updateItem(Long id, MenuItemDTO dto, Long userId) {
		MenuItem item = menuItemRepository.findById(id)
			.orElseThrow(() -> new com.hilcoe.crms.exception.MenuItemNotFoundException("Menu item not found with id: " + id));
		MenuItem before = new MenuItem();
		before.setMenuItemId(item.getMenuItemId());
		before.setCategory(item.getCategory());
		before.setName(item.getName());
		before.setDescription(item.getDescription());
		before.setPrice(item.getPrice());
		before.setIsAvailable(item.getIsAvailable());
		Category category = categoryRepository.findById(dto.getCategoryId())
			.orElseThrow(() -> new com.hilcoe.crms.exception.MenuItemNotFoundException("Category not found with id: " + dto.getCategoryId()));
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

	public void deleteItem(Long id, Long userId) {
		MenuItem item = menuItemRepository.findById(id)
			.orElseThrow(() -> new com.hilcoe.crms.exception.MenuItemNotFoundException(id));
		auditLogService.log(userId, "DELETE", "MenuItem", item.getMenuItemId(), item);
		menuItemRepository.deleteById(id);
	}

	public List<MenuItemResponseDTO> getMenu() {
		return menuItemRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
	}

	public PaginatedResponseDTO<MenuItemResponseDTO> getMenuPaginated(Pageable pageable) {
        Page<MenuItem> page = menuItemRepository.findAll(pageable);
        List<MenuItemResponseDTO> content = page.getContent().stream().map(this::toResponseDTO).collect(Collectors.toList());
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
        return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages,
            hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

	public MenuItemResponseDTO getMenuItemById(Long id) {
        MenuItem item = menuItemRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.MenuItemNotFoundException(id));
        return toResponseDTO(item);
    }

    public PaginatedResponseDTO<MenuItemResponseDTO> getMenuByCategoryPaginated(Long categoryId, Pageable pageable) {
        Page<MenuItem> page = menuItemRepository.findByCategory_CategoryId(categoryId, pageable);
        List<MenuItemResponseDTO> content = page.getContent().stream().map(this::toResponseDTO).collect(Collectors.toList());
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
        return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages,
            hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

	private MenuItemResponseDTO toResponseDTO(MenuItem item) {
		return new MenuItemResponseDTO(
			item.getMenuItemId(),
			item.getCategory().getCategoryId(),
			item.getName(),
			item.getDescription(),
			item.getPrice(),
			item.getIsAvailable()
		);
	}
}