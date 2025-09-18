package com.hilcoe.crms.exception;

public class RestaurantTableNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RestaurantTableNotFoundException(Long id) {
		super("Restaurant table not found with id: " + id);
	}

	public RestaurantTableNotFoundException(String message) {
		super(message);
	}
}