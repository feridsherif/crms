package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ReservationFullResponseDTO {
	@NotNull
	private Long reservationId;
	@NotNull
	private Long customerId;
	@NotNull
	private Long tableId;
	@NotNull
	private Long branchId;
	@NotNull
	private LocalDateTime reservationTime;
	@NotNull
	private int partySize;
	@NotBlank
	private String status;

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

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}