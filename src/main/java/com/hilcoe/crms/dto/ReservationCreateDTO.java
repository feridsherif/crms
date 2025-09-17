package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;

public class ReservationCreateDTO {
	@NotNull
	private Long customerId;
	@NotNull
	private Long tableId;
	@NotNull
	private LocalDateTime reservationTime;
	@Min(1)
	private int partySize;
	@NotNull
	private Long branchId;

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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
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

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
}