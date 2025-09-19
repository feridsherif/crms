# AuditLogService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the AuditLogService.

```java
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilcoe.crms.entity.AuditLog;
import com.hilcoe.crms.repository.AuditLogRepository;
```
- Imports classes for date/time, dependency injection, JSON mapping, entities, and repositories.

## Service Declaration
```java
@Service
public class AuditLogService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private ObjectMapper objectMapper;
```
- Injects dependencies for audit log persistence and JSON mapping.

### Log Method
```java
    public void log(Long userId, String action, String entity, Long entityId, Object dataObj) {
        String data = null;
        try {
            if (dataObj != null) {
                data = objectMapper.writeValueAsString(dataObj);
            }
        } catch (Exception e) {
            data = "{\"error\":\"Failed to serialize entity data\"}";
        }
        AuditLog log = new AuditLog();
        log.setUserId(userId);
        log.setAction(action);
        log.setEntity(entity);
        log.setEntityId(entityId != null ? entityId : 0L);
        log.setTimestamp(LocalDateTime.now());
        log.setData(data);
        auditLogRepository.save(log);
    }
```
- Serializes the data object to JSON, creates an AuditLog entity, sets all fields, and saves it to the repository. Handles serialization errors gracefully.

---

This file provides a detailed explanation of each section and method in the `AuditLogService` class, helping developers understand its structure and logic.
