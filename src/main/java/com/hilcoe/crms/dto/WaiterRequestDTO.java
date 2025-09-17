package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class WaiterRequestDTO {
	@NotNull
	private Long tableId;
	@NotBlank
	private String requestType;

	public WaiterRequestDTO() {
		super();
	}

	public WaiterRequestDTO(@NotNull Long tableId, @NotBlank String requestType) {
		super();
		this.tableId = tableId;
		this.requestType = requestType;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}