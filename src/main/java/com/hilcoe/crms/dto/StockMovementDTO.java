package com.hilcoe.crms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class StockMovementDTO {
	@NotNull
	private Long createdBy;
	@NotNull
	private Long inventoryItemId;
	private Long movementId;
	@NotNull
	private BigDecimal quantityChange;
	@NotNull
	private String reason;

	public StockMovementDTO() {
		super();
	}

	public StockMovementDTO(Long inventoryItemId, BigDecimal quantityChange, String reason, Long createdBy) {
		this.inventoryItemId = inventoryItemId;
		this.quantityChange = quantityChange;
		this.reason = reason;
		this.createdBy = createdBy;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public Long getInventoryItemId() {
		return inventoryItemId;
	}

	public Long getMovementId() {
		return movementId;
	}

	public BigDecimal getQuantityChange() {
		return quantityChange;
	}

	public String getReason() {
		return reason;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public void setInventoryItemId(Long inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public void setMovementId(Long movementId) {
		this.movementId = movementId;
	}

	public void setQuantityChange(BigDecimal quantityChange) {
		this.quantityChange = quantityChange;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}