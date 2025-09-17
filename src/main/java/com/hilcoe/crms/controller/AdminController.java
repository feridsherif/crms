package com.hilcoe.crms.controller;

import com.hilcoe.crms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/reports")
    public ResponseEntity<Object> getReports() {
        Object report = adminService.getReports();
        return ResponseEntity.ok(new ApiResponse<>("success", "Reports fetched", report));
    }
}