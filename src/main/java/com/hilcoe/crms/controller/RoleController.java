package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.dto.RoleResponseDTO;
import com.hilcoe.crms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDTO>> createRole(@Valid @RequestBody RoleDTO dto) {
        RoleDTO createdRole = roleService.createRole(dto);
        ApiResponse<RoleDTO> response = new ApiResponse<>("success", "Role created successfully", createdRole);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponseDTO>>> getAllRoles() {
        List<RoleResponseDTO> roles = roleService.getAllRoles();
        ApiResponse<List<RoleResponseDTO>> response = new ApiResponse<>("success", "Roles retrieved successfully", roles);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> getRoleById(@PathVariable Long id) {
        RoleResponseDTO role = roleService.getRoleById(id);
        ApiResponse<RoleResponseDTO> response = new ApiResponse<>("success", "Role retrieved successfully", role);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDTO dto) {
        RoleDTO updatedRole = roleService.updateRole(id, dto);
        ApiResponse<RoleDTO> response = new ApiResponse<>("success", "Role updated successfully", updatedRole);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        ApiResponse<Void> response = new ApiResponse<>("success", "Role deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<PaginatedResponseDTO<RoleDTO>>> getRolesPaginated(@RequestParam(defaultValue = "0") int page,
                                                                                       @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<RoleDTO> roles = roleService.getRolesPaginated(PageRequest.of(page, size));
        ApiResponse<PaginatedResponseDTO<RoleDTO>> response = new ApiResponse<>("success", "Roles retrieved successfully", roles);
        return ResponseEntity.ok(response);
    }
}