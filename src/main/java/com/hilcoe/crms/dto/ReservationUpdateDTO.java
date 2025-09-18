package com.hilcoe.crms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReservationUpdateDTO {
	@Min(1)
	private int partySize;
	@NotNull
	private LocalDateTime reservationTime;

	public ReservationUpdateDTO() {
		super();
	}

	public ReservationUpdateDTO(@NotNull LocalDateTime reservationTime, @Min(1) int partySize) {
		super();
		this.reservationTime = reservationTime;
		this.partySize = partySize;
	}

	public int getPartySize() {
		return partySize;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}
}