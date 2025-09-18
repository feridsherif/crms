package com.hilcoe.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.security.JwtUtil;
import com.hilcoe.crms.service.StaffService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
	public static class AssignRoleRequest {
		public Long roleId;

		public Long getRoleId() {
			return roleId;
		}

		public void setRoleId(Long roleId) {
			this.roleId = roleId;
		}
	}

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private StaffService staffService;

	@PostMapping
	@PreAuthorize("hasAuthority('STAFF_CREATE')")
	public ResponseEntity<Object> addStaff(@Valid @RequestBody StaffDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		StaffResponseDTO response = staffService.addStaff(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Staff added", response));
	}

	@PatchMapping("/{id}/role")
	@PreAuthorize("hasAuthority('STAFF_ASSIGN_ROLE')")
	public ResponseEntity<Object> assignRole(@PathVariable Long id, @RequestBody AssignRoleRequest req,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		staffService.assignRole(id, req.getRoleId(), userId);
		return ResponseEntity.ok(ApiResponse.success("Role assigned", null));
	}

	@GetMapping
	public ResponseEntity<Object> getStaff() {
		java.util.List<StaffResponseDTO> staff = staffService.getStaff();
		return ResponseEntity.ok(ApiResponse.success("Staff fetched", staff));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getStaffById(@PathVariable Long id) {
		StaffResponseDTO staff = staffService.getStaffById(id);
		return ResponseEntity.ok(ApiResponse.success("Staff fetched", staff));
	}

	@GetMapping("/paginated")
	public ResponseEntity<Object> getStaffPaginated(@RequestParam(required = false) Long userId,
			@RequestParam(required = false) Long roleId, @RequestParam(required = false) String contact,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "staffId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<StaffResponseDTO> staff = staffService.getStaffPaginatedAdvanced(userId, roleId, contact,
				page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Staff fetched", staff));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('STAFF_DELETE')")
	public ResponseEntity<Object> removeStaff(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		staffService.removeStaff(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Staff removed", null));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('STAFF_UPDATE')")
	public ResponseEntity<Object> updateStaff(@PathVariable Long id, @Valid @RequestBody StaffDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		StaffResponseDTO response = staffService.updateStaff(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Staff updated", response));
	}
}