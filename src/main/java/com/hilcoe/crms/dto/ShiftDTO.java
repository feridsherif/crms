package com.hilcoe.crms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class ShiftDTO {
    @NotNull(message = "staffId is required")
    private Long staffId;
    @NotNull(message = "branchId is required")
    private Long branchId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Getters and setters
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }
    public Long getBranchId() { return branchId; }
    public void setBranchId(Long branchId) { this.branchId = branchId; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
}