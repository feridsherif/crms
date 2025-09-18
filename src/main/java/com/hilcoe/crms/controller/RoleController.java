package com.hilcoe.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.dto.RoleResponseDTO;
import com.hilcoe.crms.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CREATE')")
	public ResponseEntity<ApiResponse<RoleDTO>> createRole(@Valid @RequestBody RoleDTO dto,
			Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		RoleDTO createdRole = roleService.createRole(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Role created successfully", createdRole));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_DELETE')")
	public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable Long id, Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		roleService.deleteRole(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Role deleted successfully", null));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_READ')")
	public ResponseEntity<ApiResponse<List<RoleResponseDTO>>> getAllRoles() {
		List<RoleResponseDTO> roles = roleService.getAllRoles();
		return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully", roles));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_READ')")
	public ResponseEntity<ApiResponse<RoleResponseDTO>> getRoleById(@PathVariable Long id) {
		RoleResponseDTO role = roleService.getRoleById(id);
		return ResponseEntity.ok(ApiResponse.success("Role retrieved successfully", role));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('ROLE_READ')")
	public ResponseEntity<ApiResponse<PaginatedResponseDTO<RoleDTO>>> getRolesPaginated(
			@RequestParam(required = false) String name, @RequestParam(required = false) String description,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "roleId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<RoleDTO> roles = roleService.getRolesPaginatedAdvanced(name, description, page, size,
				sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully", roles));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_UPDATE')")
	public ResponseEntity<ApiResponse<RoleDTO>> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDTO dto,
			Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		RoleDTO updatedRole = roleService.updateRole(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Role updated successfully", updatedRole));
	}
}