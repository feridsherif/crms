package com.hilcoe.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.security.JwtUtil;
import com.hilcoe.crms.service.MenuService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MenuService menuService;

	@PostMapping
	@PreAuthorize("hasAuthority('MENU_CREATE')")
	public ResponseEntity<Object> addMenuItem(@Valid @RequestBody MenuItemDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		MenuItemResponseDTO response = menuService.addItem(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Menu item added", response));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('MENU_DELETE')")
	public ResponseEntity<Object> deleteMenuItem(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		menuService.deleteItem(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Menu item deleted", null));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('MENU_READ')")
	public ResponseEntity<Object> getMenu() {
		List<MenuItemResponseDTO> menu = menuService.getMenu();
		return ResponseEntity.ok(ApiResponse.success("Menu fetched", menu));
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<Object> getMenuByCategoryPaginated(@PathVariable Long categoryId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		PaginatedResponseDTO<MenuItemResponseDTO> menu = menuService.getMenuByCategoryPaginated(categoryId,
				PageRequest.of(page, size));
		return ResponseEntity.ok(ApiResponse.success("Menu fetched", menu));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getMenuItemById(@PathVariable Long id) {
		MenuItemResponseDTO menuItem = menuService.getMenuItemById(id);
		return ResponseEntity.ok(ApiResponse.success("Menu item fetched", menuItem));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('MENU_READ')")
	public ResponseEntity<Object> getMenuPaginated(@RequestParam(required = false) String name,
			@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Boolean isAvailable,
			@RequestParam(required = false) java.math.BigDecimal minPrice,
			@RequestParam(required = false) java.math.BigDecimal maxPrice, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "menuItemId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<MenuItemResponseDTO> menu = menuService.getMenuPaginatedAdvanced(name, categoryId,
				isAvailable, minPrice, maxPrice, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Menu fetched", menu));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('MENU_UPDATE')")
	public ResponseEntity<Object> updateMenuItem(@PathVariable Long id, @Valid @RequestBody MenuItemDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		MenuItemResponseDTO response = menuService.updateItem(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Menu item updated", response));
	}
}