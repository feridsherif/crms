package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;

public class ReservationUpdateDTO {
	@NotNull
	private LocalDateTime reservationTime;
	@Min(1)
	private int partySize;

	public ReservationUpdateDTO() {
		super();
	}

	public ReservationUpdateDTO(@NotNull LocalDateTime reservationTime, @Min(1) int partySize) {
		super();
		this.reservationTime = reservationTime;
		this.partySize = partySize;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public int getPartySize() {
		return partySize;
	}

	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}
}