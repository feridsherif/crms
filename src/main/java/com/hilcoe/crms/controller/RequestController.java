package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hilcoe.crms.security.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Object> createRequest(@Valid @RequestBody WaiterRequestDTO dto, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "Missing or invalid Authorization header", null));
        }
        String token = authHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);
        if (userId == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "User ID not found in token", null));
        }
        WaiterRequestResponseDTO response = requestService.createRequest(dto, userId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Request created", response));
    }

    @PatchMapping("/{id}/acknowledge")
    public ResponseEntity<Object> acknowledgeRequest(@PathVariable Long id, @RequestBody com.hilcoe.crms.dto.WaiterActionDTO dto) {
        requestService.acknowledgeRequest(id, dto.getWaiterId());
        return ResponseEntity.ok(new ApiResponse<>("success", "Request acknowledged", null));
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<Object> resolveRequest(@PathVariable Long id, @RequestBody com.hilcoe.crms.dto.WaiterActionDTO dto) {
        requestService.resolveRequest(id, dto.getWaiterId());
        return ResponseEntity.ok(new ApiResponse<>("success", "Request resolved", null));
    }
}