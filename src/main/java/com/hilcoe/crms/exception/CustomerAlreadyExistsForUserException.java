package com.hilcoe.crms.exception;

public class CustomerAlreadyExistsForUserException extends RuntimeException {
    public CustomerAlreadyExistsForUserException(Long userId) {
        super("A customer already exists for user with id: " + userId);
    }
}
