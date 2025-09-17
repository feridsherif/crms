package com.hilcoe.crms.exception;

public class ReservationUpdateNotAllowedException extends RuntimeException {
    public ReservationUpdateNotAllowedException(Long id) {
        super("Reservation with id " + id + " cannot be updated because it is CANCELLED or COMPLETED.");
    }
    public ReservationUpdateNotAllowedException(String message) {
        super(message);
    }
}
