package com.hilcoe.crms.exception;

public class WaiterRequestNotFound extends RuntimeException {
    public WaiterRequestNotFound(Long requestId) {
        super("Waiter request not found with id: " + requestId);
    }
}