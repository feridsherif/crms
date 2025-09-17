package com.hilcoe.crms.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Long id) {
        super("Order not found with id: " + id);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}