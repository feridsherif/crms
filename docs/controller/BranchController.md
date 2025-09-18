# BranchController.java - Line by Line Explanation

This document provides a line-by-line explanation of the `BranchController` class, which is a REST controller for managing restaurant branches in the CRMS system.

---

```java
package com.hilcoe.crms.controller;
```
Declares the package for this controller class.

```java
import java.util.List;
```
Imports the List interface for handling collections of objects.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
```
Imports various Spring annotations and classes for REST API endpoints, HTTP handling, and security.

```java
import com.hilcoe.crms.dto.BranchDTO;
import com.hilcoe.crms.dto.BranchResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.security.JwtUtil;
import com.hilcoe.crms.service.BranchService;
```
Imports project-specific DTOs, JWT utility, and the service layer for branch operations.

```java
import jakarta.validation.Valid;
```
Imports annotation for validating request bodies.

```java
@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {
```
Defines the class as a REST controller with base URL `/api/v1/branches`.

```java
	@Autowired
	private BranchService branchService;
```
Injects the BranchService for business logic related to branches.

```java
	@Autowired
	private JwtUtil jwtUtil;
```
Injects the JwtUtil for extracting user info from JWT tokens.

---

### Endpoint: Add Branch

```java
	@PostMapping
	@PreAuthorize("hasAuthority('BRANCH_CREATE')")
	public ResponseEntity<Object> addBranch(@Valid @RequestBody BranchDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		BranchResponseDTO response = branchService.addBranch(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Branch added", response));
	}
```
- `@PostMapping`: Maps HTTP POST requests to this method.
- `@PreAuthorize("hasAuthority('BRANCH_CREATE')")`: Restricts access to users with 'BRANCH_CREATE' authority.
- `public ResponseEntity<Object> addBranch(...)`: Method signature for the endpoint.
- `@Valid @RequestBody BranchDTO dto`: Binds and validates the request body as a BranchDTO object.
- `@RequestHeader("Authorization") String authHeader`: Reads the Authorization header from the request.
- `String token = authHeader.replace("Bearer ", "");`: Removes the 'Bearer ' prefix to extract the JWT token.
- `Long userId = jwtUtil.extractUserId(token);`: Extracts the user ID from the JWT token using JwtUtil.
- `BranchResponseDTO response = branchService.addBranch(dto, userId);`: Calls the service to add a new branch, passing the DTO and user ID.
- `return ResponseEntity.status(201)...`: Returns a 201 Created response with a success message and the created branch data.

---

### Endpoint: Delete Branch

```java
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('BRANCH_DELETE')")
	public ResponseEntity<ApiResponse<Void>> deleteBranch(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		branchService.deleteBranch(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Branch deleted", null));
	}
```
- `@DeleteMapping("/{id}")`: Maps HTTP DELETE requests with a path variable (branch ID).
- `@PreAuthorize("hasAuthority('BRANCH_DELETE')")`: Restricts access to users with 'BRANCH_DELETE' authority.
- `public ResponseEntity<ApiResponse<Void>> deleteBranch(...)`: Method signature for the endpoint.
- `@PathVariable Long id`: Extracts the branch ID from the URL.
- `@RequestHeader("Authorization") String authHeader`: Reads the Authorization header.
- `String token = authHeader.replace("Bearer ", "");`: Extracts the JWT token.
- `Long userId = jwtUtil.extractUserId(token);`: Gets the user ID from the token.
- `branchService.deleteBranch(id, userId);`: Calls the service to delete the branch.
- `return ResponseEntity.ok(...)`: Returns a 200 OK response with a success message.

---

### Endpoint: Get All Branches

```java
	@GetMapping
	@PreAuthorize("hasAuthority('BRANCH_READ')")
	public ResponseEntity<Object> getBranches() {
		List<BranchResponseDTO> branches = branchService.getBranches();
		return ResponseEntity.ok(ApiResponse.success("Branches fetched", branches));
	}
```
- `@GetMapping`: Maps HTTP GET requests to this method.
- `@PreAuthorize("hasAuthority('BRANCH_READ')")`: Restricts access to users with 'BRANCH_READ' authority.
- `public ResponseEntity<Object> getBranches()`: Method signature for the endpoint.
- `branchService.getBranches()`: Calls the service to fetch all branches.
- `return ResponseEntity.ok(...)`: Returns a 200 OK response with the list of branches.

---

### Endpoint: Get Branches (Paginated)

```java
	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('BRANCH_READ')")
	public ResponseEntity<Object> getBranchesPaginated(@RequestParam(required = false) String name,
			@RequestParam(required = false) String address, @RequestParam(required = false) String phone,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "branchId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<BranchResponseDTO> branches = branchService.getBranchesPaginatedAdvanced(name, address,
				phone, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Branches fetched", branches));
	}
```
- `@GetMapping("/paginated")`: Maps GET requests to /paginated.
- `@PreAuthorize("hasAuthority('BRANCH_READ')")`: Restricts access to users with 'BRANCH_READ' authority.
- `public ResponseEntity<Object> getBranchesPaginated(...)`: Method signature for the endpoint.
- `@RequestParam(required = false) String name, ...`: Accepts optional query parameters for filtering (name, address, phone), pagination (page, size), and sorting (sortBy, direction).
- `branchService.getBranchesPaginatedAdvanced(...)`: Calls the service to fetch paginated and filtered branches.
- `return ResponseEntity.ok(...)`: Returns a 200 OK response with paginated branch data.

---

### Endpoint: Update Branch

```java
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('BRANCH_UPDATE')")
	public ResponseEntity<Object> updateBranch(@PathVariable Long id, @Valid @RequestBody BranchDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		BranchResponseDTO response = branchService.updateBranch(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Branch updated", response));
	}
```
- `@PutMapping("/{id}")`: Maps HTTP PUT requests with a branch ID.
- `@PreAuthorize("hasAuthority('BRANCH_UPDATE')")`: Restricts access to users with 'BRANCH_UPDATE' authority.
- `public ResponseEntity<Object> updateBranch(...)`: Method signature for the endpoint.
- `@PathVariable Long id`: Gets the branch ID from the URL.
- `@Valid @RequestBody BranchDTO dto`: Validates and deserializes the request body.
- `@RequestHeader("Authorization") String authHeader`: Reads the Authorization header.
- `String token = authHeader.replace("Bearer ", "");`: Extracts the JWT token.
- `Long userId = jwtUtil.extractUserId(token);`: Gets the user ID from the token.
- `branchService.updateBranch(id, dto, userId);`: Calls the service to update the branch.
- `return ResponseEntity.ok(...)`: Returns a 200 OK response with the updated branch data.

---

```java
}
```
End of the class.

---

This controller provides endpoints for branch CRUD operations, with security and JWT-based user identification, delegating business logic to the service layer.