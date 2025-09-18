package com.hilcoe.crms.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderCreateDTO {
	@NotNull
	private Long branchId;
	@NotNull
	@NotEmpty
	@Valid
	private List<OrderItemDTO> items;
	@NotNull
	private Long tableId;

	public OrderCreateDTO() {
		super();
	}

	public OrderCreateDTO(@NotNull Long branchId, @NotNull Long tableId, @NotNull List<OrderItemDTO> items) {
		super();
		this.branchId = branchId;
		this.tableId = tableId;
		this.items = items;
	}

	public Long getBranchId() {
		return branchId;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
}