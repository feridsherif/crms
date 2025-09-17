package com.hilcoe.crms.exception;

public class PermissionNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public PermissionNotFoundException(Long id) {
        super("Permission not found with id: " + id);
    }
}
