package com.hilcoe.crms.exception;

public class CustomerAlreadyExistsForUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistsForUserException(Long userId) {
		super("A customer already exists for user with id: " + userId);
	}

	public CustomerAlreadyExistsForUserException(String message) {
		super(message);
	}
}