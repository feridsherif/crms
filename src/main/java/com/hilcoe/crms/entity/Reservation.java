package com.hilcoe.crms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long reservationId;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "table_id", nullable = false)
	private Long tableId;

	@Column(name = "branch_id", nullable = false)
	private Long branchId;

	@Column(name = "reservation_time", nullable = false)
	private LocalDateTime reservationTime;

	@Column(name = "party_size", nullable = false)
	private int partySize;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ReservationStatus status;

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

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	// Enum for reservation status
	public static enum ReservationStatus {
		PENDING, CONFIRMED, CANCELLED, COMPLETED
	}
}