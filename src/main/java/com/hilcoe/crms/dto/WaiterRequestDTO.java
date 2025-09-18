package com.hilcoe.crms.dto;

import com.hilcoe.crms.entity.WaiterRequest;

import jakarta.validation.constraints.NotNull;

public class WaiterRequestDTO {
	@NotNull
	private Long branchId;
	private Long customerId;
	@NotNull
	private WaiterRequest.RequestType requestType;
	@NotNull
	private Long tableId;

	public WaiterRequestDTO() {
		super();
	}

	public WaiterRequestDTO(@NotNull Long tableId, @NotNull Long branchId,
			@NotNull WaiterRequest.RequestType requestType, Long customerId) {
		super();
		this.tableId = tableId;
		this.branchId = branchId;
		this.requestType = requestType;
		this.customerId = customerId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public WaiterRequest.RequestType getRequestType() {
		return requestType;
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

	public void setRequestType(WaiterRequest.RequestType requestType) {
		this.requestType = requestType;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
}