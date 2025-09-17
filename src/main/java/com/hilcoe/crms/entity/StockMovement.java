package com.hilcoe.crms.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_movements")
public class StockMovement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movement_id")
	private Long movementId;

	@ManyToOne
	@JoinColumn(name = "inventory_item_id", nullable = false)
	private InventoryItem inventoryItem;

	@Column(name = "quantity_change", nullable = false)
	private BigDecimal quantityChange;

	@Column(name = "reason", nullable = false)
	private String reason;

	@Column(name = "created_by", nullable = false)
	private Long createdBy;

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

	public Long getMovementId() {
		return movementId;
	}

	public void setMovementId(Long movementId) {
		this.movementId = movementId;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
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