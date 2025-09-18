package com.hilcoe.crms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InventoryItemDTO {
	@NotBlank
	private String name;
	@NotNull
	private BigDecimal quantity;
	@NotNull
	private Long supplierId;
	@NotNull
	private BigDecimal threshold;
	@NotBlank
	private String unit;

	public InventoryItemDTO() {
		super();
	}

	public InventoryItemDTO(String name, String unit, BigDecimal quantity, BigDecimal threshold, Long supplierId) {
		this.name = name;
		this.unit = unit;
		this.quantity = quantity;
		this.threshold = threshold;
		this.supplierId = supplierId;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public BigDecimal getThreshold() {
		return threshold;
	}

	public String getUnit() {
		return unit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
