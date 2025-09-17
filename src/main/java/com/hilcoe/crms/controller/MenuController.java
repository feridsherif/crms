package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Object> addMenuItem(@Valid @RequestBody MenuItemDTO dto) {
        MenuItemResponseDTO response = menuService.addItem(dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Menu item added", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMenuItem(@PathVariable Long id, @Valid @RequestBody MenuItemDTO dto) {
        MenuItemResponseDTO response = menuService.updateItem(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Menu item updated", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteMenuItem(@PathVariable Long id) {
        menuService.deleteItem(id);
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        response.put("message", "Menu item deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Object> getMenu() {
        List<MenuItemResponseDTO> menu = menuService.getMenu();
        return ResponseEntity.ok(new ApiResponse<>("success", "Menu fetched", menu));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getMenuPaginated(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<MenuItemResponseDTO> menu = menuService.getMenuPaginated(PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponse<>("success", "Menu fetched", menu));
    }
}