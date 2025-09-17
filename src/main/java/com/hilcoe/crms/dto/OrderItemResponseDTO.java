package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrderItemResponseDTO {
	@NotNull
	private Long orderItemId;
	@NotNull
	private Long menuItemId;
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

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}