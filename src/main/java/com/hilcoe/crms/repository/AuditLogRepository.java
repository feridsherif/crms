package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
