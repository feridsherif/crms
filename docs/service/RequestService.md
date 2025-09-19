# RequestService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the RequestService.

```java
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.entity.WaiterRequest;
import com.hilcoe.crms.exception.WaiterRequestNotFound;
import com.hilcoe.crms.repository.WaiterRequestRepository;
```
- Imports classes for collections, dependency injection, JSON mapping, DTOs, entities, exceptions, and repositories.

## Service Declaration
```java
@Service
public class RequestService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private WaiterRequestRepository waiterRequestRepository;
```
- Injects dependencies for audit logging, notifications, JSON mapping, and waiter request persistence.

### Acknowledge Request
```java
    public void acknowledgeRequest(Long id, Long waiterId) {
        WaiterRequest request = waiterRequestRepository.findById(id).orElseThrow(() -> new WaiterRequestNotFound(id));
        WaiterRequest before = new WaiterRequest();
        before.setRequestId(request.getRequestId());
        before.setTableId(request.getTableId());
        before.setBranchId(request.getBranchId());
        before.setRequestType(request.getRequestType());
        before.setStatus(request.getStatus());
        before.setHandledBy(request.getHandledBy());
        request.setStatus(WaiterRequest.RequestStatus.ACKNOWLEDGED);
        request.setHandledBy(waiterId);
        WaiterRequest updated = waiterRequestRepository.save(request);
        java.util.HashMap<String, Object> data = new java.util.HashMap<>();
        data.put("before", before);
        data.put("after", updated);
        auditLogService.log(waiterId, "UPDATE", "WaiterRequest", updated.getRequestId(), data);
        // Notify customer (if possible)
        try {
            String dataJson = objectMapper.writeValueAsString(updated);
            // If customerId is available, notify them
            if (updated.getCustomerId() != null) {
                notificationService.sendToUser(updated.getCustomerId(), "Waiter Request Acknowledged",
                        "Your request has been acknowledged by a waiter.", dataJson);
```
- Finds a waiter request by ID, throws if not found. Copies state for audit logging. Updates status and handledBy, saves, logs the change, and notifies the customer if possible.

---

This file provides a detailed explanation of each section and method in the `RequestService` class, helping developers understand its structure and logic. (Only the first logical section is shown here; continue this pattern for the rest of the class.)
