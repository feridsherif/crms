package com.hilcoe.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.LoginRequestDTO;
import com.hilcoe.crms.dto.LoginResponseDTO;
import com.hilcoe.crms.security.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
		try {
			LoginResponseDTO response = authService.login(loginRequest);
			return ResponseEntity.ok(ApiResponse.success("Login successful", response));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(ApiResponse.failed(e.getMessage()));
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader(value = "Authorization", required = false) String token) {
		authService.logout(token);
		return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
	}
}