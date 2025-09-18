package com.hilcoe.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.OrderCreateDTO;
import com.hilcoe.crms.dto.OrderFullResponseDTO;
import com.hilcoe.crms.dto.OrderResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.StatusDTO;
import com.hilcoe.crms.security.JwtUtil;
import com.hilcoe.crms.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private OrderService orderService;

	@PostMapping
	@PreAuthorize("hasAuthority('ORDER_CREATE')")
	public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderCreateDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return ResponseEntity.status(401).body(ApiResponse.error("Missing or invalid Authorization header"));
		}
		String token = authHeader.substring(7);
		Long userId = jwtUtil.extractUserId(token);
		if (userId == null) {
			return ResponseEntity.status(401).body(ApiResponse.error("User ID not found in token"));
		}
		OrderResponseDTO response = orderService.createOrder(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Order created", response));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return ResponseEntity.status(401).body(ApiResponse.error("Missing or invalid Authorization header"));
		}
		String token = authHeader.substring(7);
		Long userId = jwtUtil.extractUserId(token);
		if (userId == null) {
			return ResponseEntity.status(401).body(ApiResponse.error("User ID not found in token"));
		}
		orderService.deleteOrder(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Order deleted", null));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ORDER_READ')")
	public ResponseEntity<Object> getOrder(@PathVariable Long id) {
		OrderFullResponseDTO response = orderService.getOrder(id);
		return ResponseEntity.ok(ApiResponse.success("Order fetched", response));
	}

	@GetMapping("/paginated")
	public ResponseEntity<Object> getOrdersPaginated(@RequestParam(required = false) String status,
			@RequestParam(required = false) Long branchId, @RequestParam(required = false) Long tableId,
			@RequestParam(required = false) Long staffId,
			@RequestParam(required = false) java.math.BigDecimal minTotalAmount,
			@RequestParam(required = false) java.math.BigDecimal maxTotalAmount,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "orderId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<OrderResponseDTO> orders = orderService.getOrdersPaginatedAdvanced(status, branchId,
				tableId, staffId, minTotalAmount, maxTotalAmount, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Orders fetched", orders));
	}

	@PatchMapping("/{id}/status")
	@PreAuthorize("hasAuthority('ORDER_UPDATE')")
	public ResponseEntity<Object> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return ResponseEntity.status(401).body(ApiResponse.error("Missing or invalid Authorization header"));
		}
		String token = authHeader.substring(7);
		Long userId = jwtUtil.extractUserId(token);
		if (userId == null) {
			return ResponseEntity.status(401).body(ApiResponse.error("User ID not found in token"));
		}
		OrderResponseDTO response = orderService.updateStatus(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Order status updated", response));
	}
}