package com.hilcoe.crms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReservationCreateDTO {
	@NotNull
	private Long branchId;
	@NotNull
	private Long customerId;
	@Min(1)
	private int partySize;
	@NotNull
	private LocalDateTime reservationTime;
	@NotNull
	private Long tableId;

	public ReservationCreateDTO() {
		super();
	}

	public ReservationCreateDTO(@NotNull Long customerId, @NotNull Long tableId, @NotNull LocalDateTime reservationTime,
			@Min(1) int partySize, @NotNull Long branchId) {
		super();
		this.customerId = customerId;
		this.tableId = tableId;
		this.reservationTime = reservationTime;
		this.partySize = partySize;
		this.branchId = branchId;
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

	public LocalDateTime getReservationTime() {
		return reservationTime;
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

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
}