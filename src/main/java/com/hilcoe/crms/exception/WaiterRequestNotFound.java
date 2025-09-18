package com.hilcoe.crms.exception;

public class WaiterRequestNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WaiterRequestNotFound(Long requestId) {
		super("Waiter request not found with id: " + requestId);
	}

	public WaiterRequestNotFound(String message) {
		super(message);
	}
}