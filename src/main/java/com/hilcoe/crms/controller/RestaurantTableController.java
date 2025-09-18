package com.hilcoe.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.RestaurantTableDTO;
import com.hilcoe.crms.service.RestaurantTableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurant-tables")
public class RestaurantTableController {
	@Autowired
	private RestaurantTableService restaurantTableService;

	@PostMapping
	@PreAuthorize("hasAuthority('TABLE_CREATE')")
	public ResponseEntity<ApiResponse<RestaurantTableDTO>> createTable(@Valid @RequestBody RestaurantTableDTO dto) {
		RestaurantTableDTO created = restaurantTableService.createTable(dto);
		return ResponseEntity.status(201).body(ApiResponse.success("Table created successfully", created));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('TABLE_DELETE')")
	public ResponseEntity<ApiResponse<Void>> deleteTable(@PathVariable Long id) {
		restaurantTableService.deleteTable(id);
		return ResponseEntity.ok(ApiResponse.success("Table deleted successfully", null));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('TABLE_READ')")
	public ResponseEntity<ApiResponse<List<RestaurantTableDTO>>> getAllTables() {
		List<RestaurantTableDTO> tables = restaurantTableService.getAllTables();
		return ResponseEntity.ok(ApiResponse.success("Tables retrieved successfully", tables));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('TABLE_READ')")
	public ResponseEntity<ApiResponse<PaginatedResponseDTO<RestaurantTableDTO>>> getPaginatedTables(
			@RequestParam(required = false) Integer tableNumber, @RequestParam(required = false) Long branchId,
			@RequestParam(required = false) String status, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "tableId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<RestaurantTableDTO> paginatedTables = restaurantTableService
				.getPaginatedTablesAdvanced(tableNumber, branchId, status, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Paginated tables retrieved successfully", paginatedTables));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('TABLE_READ')")
	public ResponseEntity<ApiResponse<RestaurantTableDTO>> getTableById(@PathVariable Long id) {
		RestaurantTableDTO table = restaurantTableService.getTableById(id);
		return ResponseEntity.ok(ApiResponse.success("Table retrieved successfully", table));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('TABLE_UPDATE')")
	public ResponseEntity<ApiResponse<RestaurantTableDTO>> updateTable(@PathVariable Long id,
			@Valid @RequestBody RestaurantTableDTO dto) {
		RestaurantTableDTO updated = restaurantTableService.updateTable(id, dto);
		return ResponseEntity.ok(ApiResponse.success("Table updated successfully", updated));
	}
}