package com.hilcoe.crms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StockMovementResponseDTO {
	@NotNull
	private Long createdBy;
	@NotNull
	private Long inventoryItemId;
	@NotNull
	private Long movementId;
	@NotNull
	private BigDecimal quantityChange;
	@NotBlank
	private String reason;

	public StockMovementResponseDTO() {
		super();
	}

	public StockMovementResponseDTO(@NotNull Long movementId, @NotNull Long inventoryItemId,
			@NotNull BigDecimal quantityChange, @NotBlank String reason, @NotNull Long createdBy) {
		super();
		this.movementId = movementId;
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
