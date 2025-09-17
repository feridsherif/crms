package com.hilcoe.crms.exception;

public class RoleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public RoleNotFoundException(Long id) {
        super("Role not found with id: " + id);
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}