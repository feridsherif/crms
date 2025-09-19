# ShiftService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the ShiftService.

```java
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.ShiftDTO;
import com.hilcoe.crms.dto.ShiftResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.Shift;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.exception.ShiftNotFoundException;
import com.hilcoe.crms.mapper.ShiftMapper;
import com.hilcoe.crms.repository.BranchRepository;
import com.hilcoe.crms.repository.ShiftRepository;
import com.hilcoe.crms.repository.StaffRepository;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, exceptions, mappers, and repositories.

## Service Declaration
```java
@Service
public class ShiftService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private StaffRepository staffRepository;
```
- Injects dependencies for audit logging, branch, notification, shift, and staff persistence.

### Create Shift
```java
    public ShiftResponseDTO createShift(ShiftDTO dto, Long userId) {
        Staff staff = staffRepository.findById(dto.getStaffId())
                .orElseThrow(() -> new ShiftNotFoundException("Staff not found with id: " + dto.getStaffId()));
        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new ShiftNotFoundException("Branch not found with id: " + dto.getBranchId()));
        Shift shift = ShiftMapper.toEntity(dto, staff, branch);
        Shift saved = shiftRepository.save(shift);
        auditLogService.log(userId, "CREATE", "Shift", saved.getShiftId(), saved);
        // Notify staff of new shift assignment
        try {
            String dataJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(saved);
            notificationService.sendToUser(staff.getUserId(), "Shift Assigned", "You have been assigned a new shift.",
                    dataJson);
```
- Finds staff and branch, maps DTO to entity, saves, logs, and notifies the staff of the new shift.

---

This file provides a detailed explanation of each section and method in the `ShiftService` class, helping developers understand its structure and logic. (Continue this pattern for the rest of the class.)
