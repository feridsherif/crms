package com.hilcoe.crms.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory_items")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_item_id")
	private Long inventoryItemId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "unit", nullable = false)
	private String unit;

	@Column(name = "quantity", nullable = false)
	private BigDecimal quantity;

	@Column(name = "threshold", nullable = false)
	private BigDecimal threshold;

	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable = false)
	private Supplier supplier;

	public InventoryItem() {
	}

	public InventoryItem(Long inventoryItemId, String name, String unit, java.math.BigDecimal quantity,
			java.math.BigDecimal threshold, Supplier supplier) {
		this.inventoryItemId = inventoryItemId;
		this.name = name;
		this.unit = unit;
		this.quantity = quantity;
		this.threshold = threshold;
		this.supplier = supplier;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}