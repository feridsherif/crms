# MenuController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `MenuController` class, which manages menu items (CRUD, category filtering, and pagination).

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, JwtUtil, and MenuService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MenuService menuService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/menu")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects JwtUtil and MenuService.

---

## Endpoint: Add Menu Item

```java
@PostMapping
@PreAuthorize("hasAuthority('MENU_CREATE')")
public ResponseEntity<Object> addMenuItem(@Valid @RequestBody MenuItemDTO dto,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    MenuItemResponseDTO response = menuService.addItem(dto, userId);
    return ResponseEntity.status(201).body(ApiResponse.success("Menu item added", response));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/menu`.
- Requires `MENU_CREATE` authority.
- Extracts user ID from JWT and adds a menu item.

---

## Endpoint: Delete Menu Item

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('MENU_DELETE')")
public ResponseEntity<Object> deleteMenuItem(@PathVariable Long id,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    menuService.deleteItem(id, userId);
    return ResponseEntity.ok(ApiResponse.success("Menu item deleted", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/menu/{id}`.
- Requires `MENU_DELETE` authority.
- Deletes a menu item by ID.

---

## Endpoint: Get All Menu Items

```java
@GetMapping
@PreAuthorize("hasAuthority('MENU_READ')")
public ResponseEntity<Object> getMenu() {
    List<MenuItemResponseDTO> menu = menuService.getMenu();
    return ResponseEntity.ok(ApiResponse.success("Menu fetched", menu));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/menu`.
- Requires `MENU_READ` authority.
- Returns all menu items.

---

## Endpoint: Get Menu by Category (Paginated)

```java
@GetMapping("/category/{categoryId}")
public ResponseEntity<Object> getMenuByCategoryPaginated(@PathVariable Long categoryId,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    PaginatedResponseDTO<MenuItemResponseDTO> menu = menuService.getMenuByCategoryPaginated(categoryId,
            PageRequest.of(page, size));
    return ResponseEntity.ok(ApiResponse.success("Menu fetched", menu));
}
```
- `@GetMapping("/category/{categoryId}")`: Handles GET requests to `/api/v1/menu/category/{categoryId}`.
- Returns paginated menu items for a category.

---

## Endpoint: Get Menu Item by ID

```java
@GetMapping("/{id}")
public ResponseEntity<Object> getMenuItemById(@PathVariable Long id) {
    MenuItemResponseDTO menuItem = menuService.getMenuItemById(id);
    return ResponseEntity.ok(ApiResponse.success("Menu item fetched", menuItem));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/menu/{id}`.
- Returns a menu item by ID.

---

## Endpoint: Get Menu Paginated

```java
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
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/menu/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Update Menu Item

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('MENU_UPDATE')")
public ResponseEntity<Object> updateMenuItem(@PathVariable Long id, @Valid @RequestBody MenuItemDTO dto,
        @RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    Long userId = jwtUtil.extractUserId(token);
    MenuItemResponseDTO response = menuService.updateItem(id, dto, userId);
    return ResponseEntity.ok(ApiResponse.success("Menu item updated", response));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/menu/{id}`.
- Requires `MENU_UPDATE` authority.
- Updates a menu item by ID.

---

## Summary

- This controller manages menu item creation, update, deletion, and retrieval.
- Enforces security via method-level authorization.
- Supports paginated and filtered menu retrieval, and category-based filtering.
