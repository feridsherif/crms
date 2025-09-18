package com.hilcoe.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.security.JwtUtil;
import com.hilcoe.crms.service.RequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RequestService requestService;

	@PatchMapping("/{id}/acknowledge")
	@PreAuthorize("hasAuthority('WAITER_REQUEST_UPDATE')")
	public ResponseEntity<Object> acknowledgeRequest(@PathVariable Long id,
			@RequestBody com.hilcoe.crms.dto.WaiterActionDTO dto) {
		requestService.acknowledgeRequest(id, dto.getWaiterId());
		return ResponseEntity.ok(ApiResponse.success("Request acknowledged", null));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('WAITER_REQUEST_CREATE')")
	public ResponseEntity<Object> createRequest(@Valid @RequestBody WaiterRequestDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return ResponseEntity.status(401).body(ApiResponse.error("Missing or invalid Authorization header"));
		}
		String token = authHeader.substring(7);
		Long userId = jwtUtil.extractUserId(token);
		if (userId == null) {
			return ResponseEntity.status(401).body(ApiResponse.error("User ID not found in token"));
		}
		WaiterRequestResponseDTO response = requestService.createRequest(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Request created", response));
	}

	@PatchMapping("/{id}/resolve")
	@PreAuthorize("hasAuthority('WAITER_REQUEST_UPDATE')")
	public ResponseEntity<Object> resolveRequest(@PathVariable Long id,
			@RequestBody com.hilcoe.crms.dto.WaiterActionDTO dto) {
		requestService.resolveRequest(id, dto.getWaiterId());
		return ResponseEntity.ok(ApiResponse.success("Request resolved", null));
	}
}