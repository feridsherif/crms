package com.hilcoe.crms.exception;

public class OrderDeleteNotAllowedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OrderDeleteNotAllowedException(Long id) {
		super("Order cannot be deleted for id: " + id);
	}

	public OrderDeleteNotAllowedException(String message) {
		super(message);
	}
}