# CustomerController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `CustomerController` class, which manages customer CRUD and paginated retrieval endpoints.

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and CustomerService.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/customers")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the CustomerService.

---

## Endpoint: Create Customer

```java
@PostMapping
@PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
public ResponseEntity<ApiResponse<CustomerDTO>> createCustomer(@Valid @RequestBody CustomerDTO dto) {
    CustomerDTO created = customerService.createCustomer(dto);
    return ResponseEntity.status(201).body(ApiResponse.success("Customer created successfully", created));
}
```
- `@PostMapping`: Handles POST requests to `/api/v1/customers`.
- Requires `CUSTOMER_CREATE` authority.
- Creates a new customer.

---

## Endpoint: Delete Customer

```java
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.ok(ApiResponse.success("Customer deleted successfully", null));
}
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/customers/{id}`.
- Requires `CUSTOMER_DELETE` authority.
- Deletes a customer by ID.

---

## Endpoint: Get All Customers

```java
@GetMapping
@PreAuthorize("hasAuthority('CUSTOMER_READ')")
public ResponseEntity<ApiResponse<List<CustomerDTO>>> getAllCustomers() {
    List<CustomerDTO> customers = customerService.getAllCustomers();
    return ResponseEntity.ok(ApiResponse.success("Customers retrieved successfully", customers));
}
```
- `@GetMapping`: Handles GET requests to `/api/v1/customers`.
- Requires `CUSTOMER_READ` authority.
- Returns all customers.

---

## Endpoint: Get Customer by ID

```java
@GetMapping("/{id}")
@PreAuthorize("hasAuthority('CUSTOMER_READ')")
public ResponseEntity<ApiResponse<CustomerDTO>> getCustomerById(@PathVariable Long id) {
    CustomerDTO customer = customerService.getCustomerById(id);
    return ResponseEntity.ok(ApiResponse.success("Customer retrieved successfully", customer));
}
```
- `@GetMapping("/{id}")`: Handles GET requests to `/api/v1/customers/{id}`.
- Requires `CUSTOMER_READ` authority.
- Returns a customer by ID.

---

## Endpoint: Get Paginated Customers

```java
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
```
- `@GetMapping("/paginated")`: Handles GET requests to `/api/v1/customers/paginated`.
- Supports filtering, pagination, and sorting.

---

## Endpoint: Update Customer

```java
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(@PathVariable Long id,
        @Valid @RequestBody CustomerDTO dto) {
    CustomerDTO updated = customerService.updateCustomer(id, dto);
    return ResponseEntity.ok(ApiResponse.success("Customer updated successfully", updated));
}
```
- `@PutMapping("/{id}")`: Handles PUT requests to `/api/v1/customers/{id}`.
- Requires `CUSTOMER_UPDATE` authority.
- Updates a customer by ID.

---

## Summary

- This controller manages customer creation, update, deletion, and retrieval.
- Enforces security via method-level authorization.
- Supports paginated and filtered customer retrieval.
