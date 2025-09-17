package com.hilcoe.crms.dto;

import com.hilcoe.crms.entity.WaiterRequest;
import jakarta.validation.constraints.NotNull;

public class WaiterRequestDTO {
	@NotNull
	private Long tableId;
	@NotNull
	private Long branchId;
	@NotNull
	private WaiterRequest.RequestType requestType;

	public WaiterRequestDTO() {
		super();
	}

	public WaiterRequestDTO(@NotNull Long tableId, @NotNull Long branchId, @NotNull WaiterRequest.RequestType requestType) {
		super();
		this.tableId = tableId;
		this.branchId = branchId;
		this.requestType = requestType;
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

	public WaiterRequest.RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(WaiterRequest.RequestType requestType) {
		this.requestType = requestType;
	}
}