package com.hilcoe.crms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InventoryItemResponseDTO {
	@NotNull
	private Long inventoryItemId;
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

	public InventoryItemResponseDTO() {
		super();
	}

	public InventoryItemResponseDTO(@NotNull Long inventoryItemId, @NotBlank String name, @NotBlank String unit,
			@NotNull BigDecimal quantity, @NotNull BigDecimal threshold, @NotNull Long supplierId) {
		super();
		this.inventoryItemId = inventoryItemId;
		this.name = name;
		this.unit = unit;
		this.quantity = quantity;
		this.threshold = threshold;
		this.supplierId = supplierId;
	}

	public Long getInventoryItemId() {
		return inventoryItemId;
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

	public void setInventoryItemId(Long inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
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