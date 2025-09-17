package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class InventoryItemResponseDTO {
    @NotNull
    private Long inventoryItemId;
    @NotBlank
    private String name;
    @NotBlank
    private String unit;
    @NotNull
    private BigDecimal quantity;
    @NotNull
    private BigDecimal threshold;
    @NotNull
    private Long supplierId;

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

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}