package com.hilcoe.crms.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer not found with id: " + id);
    }
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
