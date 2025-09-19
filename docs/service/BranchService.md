# BranchService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the BranchService.

```java
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.BranchDTO;
import com.hilcoe.crms.dto.BranchResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.repository.BranchRepository;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, and repositories.

## Service Declaration
```java
@Service
public class BranchService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private BranchRepository branchRepository;
```
- Injects dependencies for audit logging and branch persistence.

### Add Branch
```java
    public BranchResponseDTO addBranch(BranchDTO dto, Long userId) {
        Branch branch = new Branch();
        branch.setName(dto.getName());
        branch.setAddress(dto.getAddress());
        branch.setPhone(dto.getPhone());
        Branch saved = branchRepository.save(branch);
        auditLogService.log(userId, "CREATE", "Branch", saved.getBranchId(), saved);
        return toResponseDTO(saved);
    }
```
- Creates a new branch, sets fields, saves, logs, and returns a response DTO.

### Delete Branch
```java
    public void deleteBranch(Long id, Long userId) {
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new com.hilcoe.crms.exception.BranchNotFoundException("Branch not found with id: " + id));
        auditLogService.log(userId, "DELETE", "Branch", branch.getBranchId(), branch);
        branchRepository.deleteById(id);
    }
```
- Finds a branch by ID, logs the deletion, and deletes it.

### Get All Branches
```java
    public List<BranchResponseDTO> getBranches() {
        return branchRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
```
- Retrieves all branches, maps them to response DTOs, and returns as a list.

### Get Branches (Paginated)
```java
    public PaginatedResponseDTO<BranchResponseDTO> getBranchesPaginated(Pageable pageable) {
        Page<Branch> page = branchRepository.findAll(pageable);
        List<BranchResponseDTO> content = page.getContent().stream().map(this::toResponseDTO)
                .collect(Collectors.toList());
        // ...implementation continues...
    }
```
- Retrieves paginated branches, maps them to response DTOs, and prepares pagination info.

---

This file provides a detailed explanation of each section and method in the `BranchService` class, helping developers understand its structure and logic.
