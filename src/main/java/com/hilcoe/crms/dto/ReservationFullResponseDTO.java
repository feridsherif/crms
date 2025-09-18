package com.hilcoe.crms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReservationFullResponseDTO {
	@NotNull
	private Long branchId;
	@NotNull
	private Long customerId;
	@NotNull
	private int partySize;
	@NotNull
	private Long reservationId;
	@NotNull
	private LocalDateTime reservationTime;
	@NotBlank
	private String status;
	@NotNull
	private Long tableId;

	public ReservationFullResponseDTO() {
		super();
	}

	public ReservationFullResponseDTO(@NotNull Long reservationId, @NotNull Long customerId, @NotNull Long tableId,
			@NotNull Long branchId, @NotNull LocalDateTime reservationTime, @NotNull int partySize,
			@NotBlank String status) {
		super();
		this.reservationId = reservationId;
		this.customerId = customerId;
		this.tableId = tableId;
		this.branchId = branchId;
		this.reservationTime = reservationTime;
		this.partySize = partySize;
		this.status = status;
	}

	public Long getBranchId() {
		return branchId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public int getPartySize() {
		return partySize;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public String getStatus() {
		return status;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
}