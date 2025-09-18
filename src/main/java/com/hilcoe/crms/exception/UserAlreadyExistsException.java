package com.hilcoe.crms.exception;

public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(Long userId) {
		super("A user already exists with id: " + userId);
	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}