package com.hilcoe.crms.exception;

public class MenuItemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MenuItemNotFoundException(Long id) {
		super("Menu item not found with id: " + id);
	}

	public MenuItemNotFoundException(String message) {
		super(message);
	}
}