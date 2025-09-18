package com.hilcoe.crms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {
	// Enum for reservation status
	public static enum ReservationStatus {
		CANCELLED, COMPLETED, CONFIRMED, PENDING
	}

	@Column(name = "branch_id", nullable = false)
	private Long branchId;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "party_size", nullable = false)
	private int partySize;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long reservationId;

	@Column(name = "reservation_time", nullable = false)
	private LocalDateTime reservationTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ReservationStatus status;

	@Column(name = "table_id", nullable = false)
	private Long tableId;

	public Reservation() {
	}

	public Reservation(Long reservationId, Long customerId, Long tableId, Long branchId,
			java.time.LocalDateTime reservationTime, int partySize, ReservationStatus status) {
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

	public ReservationStatus getStatus() {
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

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
}