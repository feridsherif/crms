package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.OrderCreateDTO;
import com.hilcoe.crms.dto.OrderResponseDTO;
import com.hilcoe.crms.dto.OrderFullResponseDTO;
import com.hilcoe.crms.dto.StatusDTO;
import com.hilcoe.crms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import com.hilcoe.crms.dto.PaginatedResponseDTO;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.hilcoe.crms.security.JwtUtil;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderCreateDTO dto, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "Missing or invalid Authorization header", null));
        }
        String token = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "User ID not found in token", null));
        }
        OrderResponseDTO response = orderService.createOrder(dto, userId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Order created", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) {
        OrderFullResponseDTO response = orderService.getOrder(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Order fetched", response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusDTO dto, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "Missing or invalid Authorization header", null));
        }
        String token = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "User ID not found in token", null));
        }
        OrderResponseDTO response = orderService.updateStatus(id, dto, userId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Order status updated", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("success", false);
            response.put("message", "Missing or invalid Authorization header");
            return ResponseEntity.status(401).body(response);
        }
        String token = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);
        if (userId == null) {
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("success", false);
            response.put("message", "User ID not found in token");
            return ResponseEntity.status(401).body(response);
        }
        orderService.deleteOrder(id, userId);
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        response.put("message", "Order deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getOrdersPaginated(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<OrderFullResponseDTO> orders = orderService.getOrdersPaginated(PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponse<>("success", "Orders fetched", orders));
    }
}