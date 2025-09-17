package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.RestaurantTableDTO;
import com.hilcoe.crms.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant-tables")
public class RestaurantTableController {
    @Autowired
    private RestaurantTableService restaurantTableService;

    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantTableDTO>> createTable(@Valid @RequestBody RestaurantTableDTO dto) {
        RestaurantTableDTO created = restaurantTableService.createTable(dto);
        ApiResponse<RestaurantTableDTO> response = new ApiResponse<>("success", "Table created successfully", created);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RestaurantTableDTO>>> getAllTables() {
        List<RestaurantTableDTO> tables = restaurantTableService.getAllTables();
        ApiResponse<List<RestaurantTableDTO>> response = new ApiResponse<>("success", "Tables retrieved successfully", tables);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantTableDTO>> getTableById(@PathVariable Long id) {
        RestaurantTableDTO table = restaurantTableService.getTableById(id);
        ApiResponse<RestaurantTableDTO> response = new ApiResponse<>("success", "Table retrieved successfully", table);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantTableDTO>> updateTable(@PathVariable Long id, @Valid @RequestBody RestaurantTableDTO dto) {
        RestaurantTableDTO updated = restaurantTableService.updateTable(id, dto);
        ApiResponse<RestaurantTableDTO> response = new ApiResponse<>("success", "Table updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTable(@PathVariable Long id) {
        restaurantTableService.deleteTable(id);
        ApiResponse<Void> response = new ApiResponse<>("success", "Table deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<PaginatedResponseDTO<RestaurantTableDTO>>> getPaginatedTables(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<RestaurantTableDTO> paginatedTables = restaurantTableService.getPaginatedTables(page, size);
        ApiResponse<PaginatedResponseDTO<RestaurantTableDTO>> response = new ApiResponse<>("success", "Paginated tables retrieved successfully", paginatedTables);
        return ResponseEntity.ok(response);
    }
}