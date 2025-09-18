package com.hilcoe.crms.dto;

import com.hilcoe.crms.entity.WaiterRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WaiterRequestResponseDTO {
	@NotNull
	private Long branchId;
	private Long handledBy;
	@NotNull
	private Long requestId;
	@NotNull
	private WaiterRequest.RequestType requestType;
	@NotBlank
	private String status;
	@NotNull
	private Long tableId;

	public WaiterRequestResponseDTO() {
		super();
	}

	public WaiterRequestResponseDTO(@NotNull Long requestId, @NotNull Long tableId, @NotNull Long branchId,
			@NotNull WaiterRequest.RequestType requestType, @NotBlank String status, Long handledBy) {
		super();
		this.requestId = requestId;
		this.tableId = tableId;
		this.branchId = branchId;
		this.requestType = requestType;
		this.status = status;
		this.handledBy = handledBy;
	}

	public Long getBranchId() {
		return branchId;
	}

	public Long getHandledBy() {
		return handledBy;
	}

	public Long getRequestId() {
		return requestId;
	}

	public WaiterRequest.RequestType getRequestType() {
		return requestType;
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

	public void setHandledBy(Long handledBy) {
		this.handledBy = handledBy;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public void setRequestType(WaiterRequest.RequestType requestType) {
		this.requestType = requestType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
}