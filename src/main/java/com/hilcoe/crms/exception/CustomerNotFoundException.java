package com.hilcoe.crms.exception;

public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(Long id) {
		super("Customer not found with id: " + id);
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}
}