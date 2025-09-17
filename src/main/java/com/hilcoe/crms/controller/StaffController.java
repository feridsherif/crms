package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Object> removeStaff(@PathVariable Long id) {
        staffService.removeStaff(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Staff removed", null));
    }

    public static class AssignRoleRequest {
        public Long roleId;
        public Long getRoleId() { return roleId; }
        public void setRoleId(Long roleId) { this.roleId = roleId; }
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<Object> assignRole(@PathVariable Long id, @RequestBody AssignRoleRequest req) {
        staffService.assignRole(id, req.getRoleId());
        return ResponseEntity.ok(new ApiResponse<>("success", "Role assigned", null));
    }

    @GetMapping
    public ResponseEntity<Object> getStaff() {
        java.util.List<StaffResponseDTO> staff = staffService.getStaff();
        return ResponseEntity.ok(new ApiResponse<>("success", "Staff fetched", staff));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getStaffPaginated(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<StaffResponseDTO> staff = staffService.getStaffPaginated(PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponse<>("success", "Staff fetched", staff));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStaffById(@PathVariable Long id) {
        StaffResponseDTO staff = staffService.getStaffById(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Staff fetched", staff));
    }
}