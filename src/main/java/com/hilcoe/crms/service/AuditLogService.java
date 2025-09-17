package com.hilcoe.crms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilcoe.crms.entity.AuditLog;
import com.hilcoe.crms.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private ObjectMapper objectMapper;

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
}
