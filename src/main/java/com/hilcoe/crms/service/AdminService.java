package com.hilcoe.crms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.repository.AuditLogRepository;

@Service
public class AdminService {
	@Autowired
	private AuditLogRepository auditLogRepository;

	public Object getReports() {
		long auditLogCount = auditLogRepository.count();
		return java.util.Map.of("auditLogCount", auditLogCount);
	}
}