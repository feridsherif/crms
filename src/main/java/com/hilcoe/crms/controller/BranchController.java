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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.BranchDTO;
import com.hilcoe.crms.dto.BranchResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.security.JwtUtil;
import com.hilcoe.crms.service.BranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {
	@Autowired
	private BranchService branchService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping
	@PreAuthorize("hasAuthority('BRANCH_CREATE')")
	public ResponseEntity<Object> addBranch(@Valid @RequestBody BranchDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		BranchResponseDTO response = branchService.addBranch(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Branch added", response));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('BRANCH_DELETE')")
	public ResponseEntity<ApiResponse<Void>> deleteBranch(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		branchService.deleteBranch(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Branch deleted", null));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('BRANCH_READ')")
	public ResponseEntity<Object> getBranches() {
		List<BranchResponseDTO> branches = branchService.getBranches();
		return ResponseEntity.ok(ApiResponse.success("Branches fetched", branches));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('BRANCH_READ')")
	public ResponseEntity<Object> getBranchesPaginated(@RequestParam(required = false) String name,
			@RequestParam(required = false) String address, @RequestParam(required = false) String phone,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "branchId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<BranchResponseDTO> branches = branchService.getBranchesPaginatedAdvanced(name, address,
				phone, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Branches fetched", branches));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('BRANCH_UPDATE')")
	public ResponseEntity<Object> updateBranch(@PathVariable Long id, @Valid @RequestBody BranchDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		BranchResponseDTO response = branchService.updateBranch(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Branch updated", response));
	}
}