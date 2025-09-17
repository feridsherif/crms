package com.hilcoe.crms.exception;

public class InventoryItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public InventoryItemNotFoundException(Long id) {
        super("Inventory item not found with id: " + id);
    }

    public InventoryItemNotFoundException(String message) {
        super(message);
    }
}