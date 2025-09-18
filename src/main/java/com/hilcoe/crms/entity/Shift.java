package com.hilcoe.crms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shifts")
public class Shift {
	@ManyToOne
	@JoinColumn(name = "branch_id", nullable = false)
	private Branch branch;

	@Column(name = "end_time", nullable = false)
	private LocalDateTime endTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shift_id")
	private Long shiftId;

	@ManyToOne
	@JoinColumn(name = "staff_id", nullable = false)
	private Staff staff;

	@Column(name = "start_time", nullable = false)
	private LocalDateTime startTime;

	public Shift() {
	}

	public Shift(Long shiftId, Staff staff, Branch branch, java.time.LocalDateTime startTime,
			java.time.LocalDateTime endTime) {
		this.shiftId = shiftId;
		this.staff = staff;
		this.branch = branch;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Branch getBranch() {
		return branch;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public Staff getStaff() {
		return staff;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
}