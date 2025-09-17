package com.hilcoe.crms.exception;

public class OrderItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public OrderItemNotFoundException(Long id) {
        super("Order item not found with id: " + id);
    }
}
