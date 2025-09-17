package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class ReservationResponseDTO {
	@NotNull
	private Long reservationId;
	@NotBlank
	private String status;

	public ReservationResponseDTO() {
		super();
	}

	public ReservationResponseDTO(@NotNull Long reservationId, @NotBlank String status) {
		super();
		this.reservationId = reservationId;
		this.status = status;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}