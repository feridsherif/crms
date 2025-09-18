package com.hilcoe.crms.exception;

public class ReservationNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReservationNotFoundException(Long id) {
		super("Reservation not found with id: " + id);
	}

	public ReservationNotFoundException(String message) {
		super(message);
	}
}