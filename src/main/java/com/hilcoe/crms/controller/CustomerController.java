package com.hilcoe.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.CustomerDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping
	@PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
	public ResponseEntity<ApiResponse<CustomerDTO>> createCustomer(@Valid @RequestBody CustomerDTO dto) {
		CustomerDTO created = customerService.createCustomer(dto);
		return ResponseEntity.status(201).body(ApiResponse.success("Customer created successfully", created));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
	public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.ok(ApiResponse.success("Customer deleted successfully", null));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('CUSTOMER_READ')")
	public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAllCustomers() {
		List<CustomerDTO> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(ApiResponse.success("Customers retrieved successfully", customers));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('CUSTOMER_READ')")
	public ResponseEntity<ApiResponse<CustomerDTO>> getCustomerById(@PathVariable Long id) {
		CustomerDTO customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(ApiResponse.success("Customer retrieved successfully", customer));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('CUSTOMER_READ')")
	public ResponseEntity<ApiResponse<PaginatedResponseDTO<CustomerDTO>>> getPaginatedCustomers(
			@RequestParam(required = false) String name, @RequestParam(required = false) String email,
			@RequestParam(required = false) String phone, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "customerId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		PaginatedResponseDTO<CustomerDTO> paginated = customerService.getPaginatedCustomersAdvanced(name, email, phone,
				page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Paginated customers retrieved successfully", paginated));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
	public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(@PathVariable Long id,
			@Valid @RequestBody CustomerDTO dto) {
		CustomerDTO updated = customerService.updateCustomer(id, dto);
		return ResponseEntity.ok(ApiResponse.success("Customer updated successfully", updated));
	}
}