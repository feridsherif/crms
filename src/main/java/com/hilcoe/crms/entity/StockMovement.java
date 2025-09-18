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
@Table(name = "stock_movements")
public class StockMovement {
	@Column(name = "created_by", nullable = false)
	private Long createdBy;

	@ManyToOne
	@JoinColumn(name = "inventory_item_id", nullable = false)
	private InventoryItem inventoryItem;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movement_id")
	private Long movementId;

	@Column(name = "quantity_change", nullable = false)
	private BigDecimal quantityChange;

	@Column(name = "reason", nullable = false)
	private String reason;

	public StockMovement() {
	}

	public StockMovement(Long movementId, InventoryItem inventoryItem, java.math.BigDecimal quantityChange,
			String reason, Long createdBy) {
		this.movementId = movementId;
		this.inventoryItem = inventoryItem;
		this.quantityChange = quantityChange;
		this.reason = reason;
		this.createdBy = createdBy;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
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

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
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