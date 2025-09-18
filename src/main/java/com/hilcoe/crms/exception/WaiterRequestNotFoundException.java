package com.hilcoe.crms.exception;

public class WaiterRequestNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WaiterRequestNotFoundException(Long id) {
		super("Waiter request not found with id: " + id);
	}

	public WaiterRequestNotFoundException(String message) {
		super(message);
	}
}