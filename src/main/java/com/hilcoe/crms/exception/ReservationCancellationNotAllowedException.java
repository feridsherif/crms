package com.hilcoe.crms.exception;

public class ReservationCancellationNotAllowedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReservationCancellationNotAllowedException(Long id) {
		super("Reservation with id " + id + " cannot be cancelled because it is already COMPLETED.");
	}

	public ReservationCancellationNotAllowedException(String message) {
		super(message);
	}
}
