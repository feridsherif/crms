package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

public class OrderFullResponseDTO {
	@NotNull
	private Long orderId;
	@NotNull
	private Long branchId;
	@NotNull
	private Long tableId;
	@NotNull
	private Long staffId;
	@NotBlank
	private String status;
	@NotNull
	private BigDecimal totalAmount;
	@NotNull
	private List<OrderItemResponseDTO> items;

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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<OrderItemResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponseDTO> items) {
		this.items = items;
	}
}