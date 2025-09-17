package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class OrderItemDTO {
	@NotNull
	private Long menuItemId;
	@Min(1)
	private int quantity;

	public OrderItemDTO() {
		super();
	}

	public OrderItemDTO(@NotNull Long menuItemId, @Min(1) int quantity) {
		super();
		this.menuItemId = menuItemId;
		this.quantity = quantity;
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
}