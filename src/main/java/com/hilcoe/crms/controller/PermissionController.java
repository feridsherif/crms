package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.PermissionDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/permissions")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionDTO>> createPermission(@Valid @RequestBody PermissionDTO dto) {
        PermissionDTO createdPermission = permissionService.createPermission(dto);
        ApiResponse<PermissionDTO> response = new ApiResponse<>("success", "Permission created successfully", createdPermission);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionDTO>>> getAllPermissions() {
        List<PermissionDTO> permissions = permissionService.getAllPermissions();
        ApiResponse<List<PermissionDTO>> response = new ApiResponse<>("success", "Permissions retrieved successfully", permissions);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionDTO>> getPermissionById(@PathVariable Long id) {
        PermissionDTO permission = permissionService.getPermissionById(id);
        ApiResponse<PermissionDTO> response = new ApiResponse<>("success", "Permission retrieved successfully", permission);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionDTO>> updatePermission(@PathVariable Long id, @Valid @RequestBody PermissionDTO dto) {
        PermissionDTO updatedPermission = permissionService.updatePermission(id, dto);
        ApiResponse<PermissionDTO> response = new ApiResponse<>("success", "Permission updated successfully", updatedPermission);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        ApiResponse<Void> response = new ApiResponse<>("success", "Permission deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<PaginatedResponseDTO<PermissionDTO>>> getPermissionsPaginated(@RequestParam(defaultValue = "0") int page,
                                                                                                 @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<PermissionDTO> permissions = permissionService.getPermissionsPaginated(PageRequest.of(page, size));
        ApiResponse<PaginatedResponseDTO<PermissionDTO>> response = new ApiResponse<>("success", "Permissions retrieved successfully", permissions);
        return ResponseEntity.ok(response);
    }
}