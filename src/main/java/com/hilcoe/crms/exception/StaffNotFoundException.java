package com.hilcoe.crms.exception;

public class StaffNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StaffNotFoundException(Long id) {
		super("Staff not found with id: " + id);
	}

	public StaffNotFoundException(String message) {
		super(message);
	}
}