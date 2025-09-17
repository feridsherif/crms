package com.hilcoe.crms.exception;

public class OrderDeleteNotAllowedException extends RuntimeException {
    public OrderDeleteNotAllowedException(String message) {
        super(message);
    }
}
