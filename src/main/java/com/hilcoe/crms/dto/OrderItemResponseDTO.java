package com.hilcoe.crms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class OrderItemResponseDTO {
	@NotNull
	private Long menuItemId;
	@NotNull
	private Long orderItemId;
	@NotNull
	private int quantity;
	@NotNull
	private BigDecimal unitPrice;

	public OrderItemResponseDTO() {
		super();
	}

	public OrderItemResponseDTO(@NotNull Long orderItemId, @NotNull Long menuItemId, @NotNull int quantity,
			@NotNull BigDecimal unitPrice) {
		super();
		this.orderItemId = orderItemId;
		this.menuItemId = menuItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public Long getMenuItemId() {
		return menuItemId;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}