package com.hilcoe.crms.exception;

public class ReservationStatusUpdateNotAllowedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReservationStatusUpdateNotAllowedException(Long id, String targetStatus) {
		super("Reservation with id " + id + " cannot be updated to status '" + targetStatus
				+ "' due to its current status.");
	}

	public ReservationStatusUpdateNotAllowedException(String message) {
		super(message);
	}
}