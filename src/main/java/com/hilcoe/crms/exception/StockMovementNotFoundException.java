package com.hilcoe.crms.exception;

public class StockMovementNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public StockMovementNotFoundException(Long id) {
        super("Stock movement not found with id: " + id);
    }
}
