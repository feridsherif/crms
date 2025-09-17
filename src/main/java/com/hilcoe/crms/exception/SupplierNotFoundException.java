package com.hilcoe.crms.exception;

public class SupplierNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public SupplierNotFoundException(Long id) {
        super("Supplier not found with id: " + id);
    }

    public SupplierNotFoundException(String message) {
        super(message);
    }
}