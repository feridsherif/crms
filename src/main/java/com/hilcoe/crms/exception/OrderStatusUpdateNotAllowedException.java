package com.hilcoe.crms.exception;

public class OrderStatusUpdateNotAllowedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OrderStatusUpdateNotAllowedException(Long id) {
		super("Order status update not allowed for order id: " + id);
	}

	public OrderStatusUpdateNotAllowedException(String message) {
		super(message);
	}
}