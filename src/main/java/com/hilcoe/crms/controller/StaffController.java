package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping
    public ResponseEntity<Object> addStaff(@Valid @RequestBody StaffDTO dto) {
        StaffResponseDTO response = staffService.addStaff(dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Staff added", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStaff(@PathVariable Long id, @Valid @RequestBody StaffDTO dto) {
        StaffResponseDTO response = staffService.updateStaff(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Staff updated", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> removeStaff(@PathVariable Long id) {
        staffService.removeStaff(id);
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        response.put("message", "Staff removed");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<Object> assignRole(@PathVariable Long id, @RequestParam Long roleId) {
        staffService.assignRole(id, roleId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Role assigned", null));
    }
}