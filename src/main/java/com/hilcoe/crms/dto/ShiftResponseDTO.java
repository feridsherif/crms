package com.hilcoe.crms.dto;

import java.time.LocalDateTime;

public class ShiftResponseDTO {
	private Long branchId;
	private LocalDateTime endTime;
	private Long shiftId;
	private Long staffId;
	private LocalDateTime startTime;

	public Long getBranchId() {
		return branchId;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	// Getters and setters
	public Long getShiftId() {
		return shiftId;
	}

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

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
}
