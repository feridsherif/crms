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
import com.hilcoe.crms.dto.UserDTO;
import com.hilcoe.crms.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	@PreAuthorize("hasAuthority('USER_CREATE')")
	public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO dto) {
		UserDTO createdUser = userService.createUser(dto);
		return ResponseEntity.status(201).body(ApiResponse.success("User created successfully", createdUser));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('USER_DELETE')")
	public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('USER_READ')")
	public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USER_READ')")
	public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
		UserDTO user = userService.getUserById(id);
		return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('USER_READ')")
	public ResponseEntity<ApiResponse<PaginatedResponseDTO<UserDTO>>> getUsersPaginated(
			@RequestParam(required = false) String username, @RequestParam(required = false) String email,
			@RequestParam(required = false) Long roleId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "userId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<UserDTO> users = userService.getUsersPaginatedAdvanced(username, email, roleId, page, size,
				sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('USER_UPDATE')")
	public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
		UserDTO updatedUser = userService.updateUser(id, dto);
		return ResponseEntity.ok(ApiResponse.success("User updated successfully", updatedUser));
	}
}