package com.hilcoe.crms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderCreateDTO {
	@NotNull
	private Long branchId;
	@NotNull
	private Long tableId;
	@NotNull
	@NotEmpty
	@Valid
	private List<OrderItemDTO> items;

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

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
}