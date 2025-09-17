package com.hilcoe.crms.exception;

public class BranchNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public BranchNotFoundException(Long id) {
        super("Branch not found with id: " + id);
    }

    public BranchNotFoundException(String message) {
        super(message);
    }
}