package com.hilcoe.crms.exception;

public class OrderStatusUpdateNotAllowedException extends RuntimeException {
    public OrderStatusUpdateNotAllowedException(String message) {
        super(message);
    }
}
