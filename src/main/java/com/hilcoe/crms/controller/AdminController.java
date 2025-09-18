package com.hilcoe.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.service.AdminService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@GetMapping("/reports")
	@PreAuthorize("hasAuthority('ADMIN_READ')")
	public ResponseEntity<Object> getReports() {
		Object report = adminService.getReports();
		return ResponseEntity.ok(ApiResponse.success("Reports fetched", report));
	}
}