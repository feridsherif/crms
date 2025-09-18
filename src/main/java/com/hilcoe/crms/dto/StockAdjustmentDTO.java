package com.hilcoe.crms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class StockAdjustmentDTO {
	@NotNull
	private BigDecimal quantityChange;
	@NotNull
	private String reason;

	public StockAdjustmentDTO() {
	}

	public StockAdjustmentDTO(BigDecimal quantityChange, String reason) {
		this.quantityChange = quantityChange;
		this.reason = reason;
	}

	public BigDecimal getQuantityChange() {
		return quantityChange;
	}

	public String getReason() {
		return reason;
	}

	public void setQuantityChange(BigDecimal quantityChange) {
		this.quantityChange = quantityChange;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
