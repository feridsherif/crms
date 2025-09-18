package com.hilcoe.crms.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderFullResponseDTO {
	@NotNull
	private Long branchId;
	@NotNull
	private List<OrderItemResponseDTO> items;
	@NotNull
	private Long orderId;
	@NotNull
	private Long staffId;
	@NotBlank
	private String status;
	@NotNull
	private Long tableId;
	@NotNull
	private BigDecimal totalAmount;

	public OrderFullResponseDTO() {
		super();
	}

	public OrderFullResponseDTO(@NotNull Long orderId, @NotNull Long branchId, @NotNull Long tableId,
			@NotNull Long staffId, @NotBlank String status, @NotNull BigDecimal totalAmount,
			@NotNull List<OrderItemResponseDTO> items) {
		super();
		this.orderId = orderId;
		this.branchId = branchId;
		this.tableId = tableId;
		this.staffId = staffId;
		this.status = status;
		this.totalAmount = totalAmount;
		this.items = items;
	}

	public Long getBranchId() {
		return branchId;
	}

	public List<OrderItemResponseDTO> getItems() {
		return items;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public String getStatus() {
		return status;
	}

	public Long getTableId() {
		return tableId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setItems(List<OrderItemResponseDTO> items) {
		this.items = items;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}