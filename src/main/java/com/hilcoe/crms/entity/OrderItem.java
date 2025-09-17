package com.hilcoe.crms.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_item_id")
	private Long orderItemId;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "menu_item_id", nullable = false)
	private MenuItem menuItem;

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

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
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

	public void setOrderId(Long orderId) {
		if (this.order == null) {
			this.order = new Order();
		}
		this.order.setOrderId(orderId);
	}

	public void setMenuItemId(Long menuItemId) {
		if (this.menuItem == null) {
			this.menuItem = new MenuItem();
		}
		this.menuItem.setMenuItemId(menuItemId);
	}
}