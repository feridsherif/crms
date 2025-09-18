package com.hilcoe.crms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class ShiftDTO {
	@NotNull(message = "branchId is required")
	private Long branchId;
	private LocalDateTime endTime;
	@NotNull(message = "staffId is required")
	private Long staffId;
	private LocalDateTime startTime;

	public Long getBranchId() {
		return branchId;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	// Getters and setters
	public Long getStaffId() {
		return staffId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
}