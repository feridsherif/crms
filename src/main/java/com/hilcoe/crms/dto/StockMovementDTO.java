package com.hilcoe.crms.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;

public class StockMovementDTO {
	@NotNull
	private Long inventoryItemId;
	@NotNull
	private BigDecimal quantityChange;
	@NotNull
	private String reason;
	@NotNull
	private Long createdBy;
	private Long movementId;

	public StockMovementDTO() {
		super();
	}

	public StockMovementDTO(Long inventoryItemId, BigDecimal quantityChange, String reason, Long createdBy) {
		this.inventoryItemId = inventoryItemId;
		this.quantityChange = quantityChange;
		this.reason = reason;
		this.createdBy = createdBy;
	}

	public Long getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(Long inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public BigDecimal getQuantityChange() {
		return quantityChange;
	}

	public void setQuantityChange(BigDecimal quantityChange) {
		this.quantityChange = quantityChange;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getMovementId() {
		return movementId;
	}

	public void setMovementId(Long movementId) {
		this.movementId = movementId;
	}
}