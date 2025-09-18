package com.hilcoe.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import com.hilcoe.crms.service.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;

	@PostMapping
	@PreAuthorize("hasAuthority('SUPPLIER_CREATE')")
	public ResponseEntity<Object> addSupplier(@Valid @RequestBody SupplierDTO dto, Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		SupplierResponseDTO response = supplierService.addSupplier(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Supplier added", response));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('SUPPLIER_DELETE')")
	public ResponseEntity<Object> deleteSupplier(@PathVariable Long id, Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		supplierService.deleteSupplier(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Supplier deleted", null));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('SUPPLIER_READ')")
	public ResponseEntity<Object> getSupplier(@PathVariable Long id) {
		SupplierResponseDTO supplier = supplierService.getSupplier(id);
		return ResponseEntity.ok(ApiResponse.success("Supplier fetched", supplier));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('SUPPLIER_READ')")
	public ResponseEntity<Object> getSuppliers() {
		List<SupplierResponseDTO> suppliers = supplierService.getSuppliers();
		return ResponseEntity.ok(ApiResponse.success("Suppliers fetched", suppliers));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('SUPPLIER_READ')")
	public ResponseEntity<Object> getSuppliersPaginated(@RequestParam(required = false) String name,
			@RequestParam(required = false) String contact, @RequestParam(required = false) String phone,
			@RequestParam(required = false) String terms, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "supplierId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<SupplierResponseDTO> suppliers = supplierService.getSuppliersPaginatedAdvanced(name,
				contact, phone, terms, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Suppliers fetched", suppliers));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SUPPLIER_UPDATE')")
	public ResponseEntity<Object> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierDTO dto,
			Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		SupplierResponseDTO response = supplierService.updateSupplier(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Supplier updated", response));
	}
}