package com.hilcoe.crms.exception;

public class AuditLogNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public AuditLogNotFoundException(Long id) {
        super("Audit log not found with id: " + id);
    }
}
