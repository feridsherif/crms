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
@Table(name = "order_items")
public class OrderItem {
	@ManyToOne
	@JoinColumn(name = "menu_item_id", nullable = false)
	private MenuItem menuItem;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_item_id")
	private Long orderItemId;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "unit_price", nullable = false)
	private BigDecimal unitPrice;

	public OrderItem() {
	}

	public OrderItem(Long orderItemId, Order order, MenuItem menuItem, int quantity, java.math.BigDecimal unitPrice) {
		this.orderItemId = orderItemId;
		this.order = order;
		this.menuItem = menuItem;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public Order getOrder() {
		return order;
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

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public void setMenuItemId(Long menuItemId) {
		if (this.menuItem == null) {
			this.menuItem = new MenuItem();
		}
		this.menuItem.setMenuItemId(menuItemId);
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setOrderId(Long orderId) {
		if (this.order == null) {
			this.order = new Order();
		}
		this.order.setOrderId(orderId);
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