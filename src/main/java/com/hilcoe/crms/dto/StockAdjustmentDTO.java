package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

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

	public void setQuantityChange(BigDecimal quantityChange) {
		this.quantityChange = quantityChange;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
