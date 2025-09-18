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
import com.hilcoe.crms.dto.PermissionDTO;
import com.hilcoe.crms.service.PermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/permissions")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;

	@PostMapping
	@PreAuthorize("hasAuthority('PERMISSION_CREATE')")
	public ResponseEntity<ApiResponse<PermissionDTO>> createPermission(@Valid @RequestBody PermissionDTO dto) {
		PermissionDTO createdPermission = permissionService.createPermission(dto);
		return ResponseEntity.status(201)
				.body(ApiResponse.success("Permission created successfully", createdPermission));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('PERMISSION_DELETE')")
	public ResponseEntity<ApiResponse<Void>> deletePermission(@PathVariable Long id) {
		permissionService.deletePermission(id);
		return ResponseEntity.ok(ApiResponse.success("Permission deleted successfully", null));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('PERMISSION_READ')")
	public ResponseEntity<ApiResponse<List<PermissionDTO>>> getAllPermissions() {
		List<PermissionDTO> permissions = permissionService.getAllPermissions();
		return ResponseEntity.ok(ApiResponse.success("Permissions retrieved successfully", permissions));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('PERMISSION_READ')")
	public ResponseEntity<ApiResponse<PermissionDTO>> getPermissionById(@PathVariable Long id) {
		PermissionDTO permission = permissionService.getPermissionById(id);
		return ResponseEntity.ok(ApiResponse.success("Permission retrieved successfully", permission));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('PERMISSION_READ')")
	public ResponseEntity<ApiResponse<PaginatedResponseDTO<PermissionDTO>>> getPermissionsPaginated(
			@RequestParam(required = false) String name, @RequestParam(required = false) String description,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "permissionId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<PermissionDTO> permissions = permissionService.getPermissionsPaginatedAdvanced(name,
				description, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Permissions retrieved successfully", permissions));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
	public ResponseEntity<ApiResponse<PermissionDTO>> updatePermission(@PathVariable Long id,
			@Valid @RequestBody PermissionDTO dto) {
		PermissionDTO updatedPermission = permissionService.updatePermission(id, dto);
		return ResponseEntity.ok(ApiResponse.success("Permission updated successfully", updatedPermission));
	}
}