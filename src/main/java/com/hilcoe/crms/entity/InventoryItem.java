package com.hilcoe.crms.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_items")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_item_id")
	private Long inventoryItemId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "quantity", nullable = false)
	private BigDecimal quantity;

	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable = false)
	private Supplier supplier;

	@Column(name = "threshold", nullable = false)
	private BigDecimal threshold;

	@Column(name = "unit", nullable = false)
	private String unit;

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

	public String getName() {
		return name;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public Supplier getSupplier() {
		return supplier;
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

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setThreshold(BigDecimal threshold) {
		this.threshold = threshold;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}