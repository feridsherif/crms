package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class StockMovementResponseDTO {
	@NotNull
	private Long movementId;
	@NotNull
	private Long inventoryItemId;
	@NotNull
	private BigDecimal quantityChange;
	@NotBlank
	private String reason;
	@NotNull
	private Long createdBy;

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

	public Long getMovementId() {
		return movementId;
	}

	public void setMovementId(Long movementId) {
		this.movementId = movementId;
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
}
