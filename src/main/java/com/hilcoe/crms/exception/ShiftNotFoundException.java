package com.hilcoe.crms.exception;

public class ShiftNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ShiftNotFoundException(Long id) {
		super("Shift not found with id: " + id);
	}

	public ShiftNotFoundException(String message) {
		super(message);
	}
}