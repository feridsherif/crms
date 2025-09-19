# SupplierService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the SupplierService.

```java
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import com.hilcoe.crms.entity.Supplier;
import com.hilcoe.crms.exception.SupplierNotFoundException;
import com.hilcoe.crms.repository.SupplierRepository;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, exceptions, and repositories.

## Service Declaration
```java
@Service
public class SupplierService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private SupplierRepository supplierRepository;
```
- Injects dependencies for audit logging and supplier persistence.

### Add Supplier
```java
    public SupplierResponseDTO addSupplier(SupplierDTO dto, Long userId) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setPhone(dto.getPhone());
        supplier.setTerms(dto.getTerms());
        Supplier saved = supplierRepository.save(supplier);
        auditLogService.log(userId, "CREATE", "Supplier", saved.getSupplierId(), saved);
        return toResponseDTO(saved);
    }
```
- Creates a new supplier, sets fields, saves, logs, and returns a response DTO.

### Delete Supplier
```java
    public void deleteSupplier(Long id, Long userId) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
        auditLogService.log(userId, "DELETE", "Supplier", supplier.getSupplierId(), supplier);
        supplierRepository.deleteById(id);
    }
```
- Finds a supplier by ID, logs the deletion, and deletes it.

### Get Supplier
```java
    public SupplierResponseDTO getSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
        return toResponseDTO(supplier);
    }
```
- Finds a supplier by ID and returns a response DTO.

### Get All Suppliers
```java
    public List<SupplierResponseDTO> getSuppliers() {
        return supplierRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
```
- Retrieves all suppliers, maps them to response DTOs, and returns as a list.

---

This file provides a detailed explanation of each section and method in the `SupplierService` class, helping developers understand its structure and logic.
