package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<Object> createRequest(@Valid @RequestBody WaiterRequestDTO dto, @RequestHeader("userId") Long userId) {
        WaiterRequestResponseDTO response = requestService.createRequest(dto, userId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Request created", response));
    }

    @PatchMapping("/{id}/acknowledge")
    public ResponseEntity<Object> acknowledgeRequest(@PathVariable Long id, @RequestHeader("waiterId") Long waiterId) {
        requestService.acknowledgeRequest(id, waiterId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Request acknowledged", null));
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<Object> resolveRequest(@PathVariable Long id) {
        requestService.resolveRequest(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Request resolved", null));
    }
}