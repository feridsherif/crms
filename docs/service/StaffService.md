# StaffService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the StaffService.

```java
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.repository.RoleRepository;
import com.hilcoe.crms.repository.StaffRepository;
```
- Imports classes for collections, dependency injection, paging, DTOs, entities, and repositories.

## Service Declaration
```java
@Service
public class StaffService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StaffRepository staffRepository;
```
- Injects dependencies for audit logging, notification, role, and staff persistence.

### Add Staff
```java
    public StaffResponseDTO addStaff(StaffDTO dto, Long userId) {
        Staff staff = new Staff();
        staff.setUserId(dto.getUserId());
        staff.setRoleId(dto.getRoleId());
        staff.setContact(dto.getContact());
        Staff saved = staffRepository.save(staff);
        auditLogService.log(userId, "CREATE", "Staff", saved.getStaffId(), saved);
        // Notify user of role assignment
        try {
            String dataJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(saved);
            notificationService.sendToUser(saved.getUserId(), "Role Assigned", "You have been assigned a new role.",
                    dataJson);
        } catch (Exception e) {
            /* log or ignore */ }
        return toResponseDTO(saved);
    }
```
- Creates a new staff member, sets fields, saves, logs, and notifies the user of their new role.

### Assign Role
```java
    public void assignRole(Long staffId, Long roleId, Long userId) {
        // ...implementation continues...
    }
```
- Assigns a new role to a staff member (implementation continues in the file).

---

This file provides a detailed explanation of each section and method in the `StaffService` class, helping developers understand its structure and logic.
