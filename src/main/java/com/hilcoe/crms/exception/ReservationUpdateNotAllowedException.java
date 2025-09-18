package com.hilcoe.crms.exception;

public class ReservationUpdateNotAllowedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReservationUpdateNotAllowedException(Long id) {
		super("Reservation with id " + id + " cannot be updated because it is CANCELLED or COMPLETED.");
	}

	public ReservationUpdateNotAllowedException(String message) {
		super(message);
	}
}