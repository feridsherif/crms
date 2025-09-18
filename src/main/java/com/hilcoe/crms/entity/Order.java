package com.hilcoe.crms.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	// Enum for order status
	public static enum OrderStatus {
		CANCELLED, COMPLETED, CREATED, IN_PROGRESS
	}

	@Column(name = "branch_id", nullable = false)
	private Long branchId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

	@Column(name = "staff_id", nullable = false)
	private Long staffId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private OrderStatus status;

	@Column(name = "table_id", nullable = false)
	private Long tableId;

	@Column(name = "total_amount", nullable = false)
	private BigDecimal totalAmount;

	public Order() {
	}

	public Order(Long orderId, Long branchId, Long tableId, Long staffId, OrderStatus status,
			java.math.BigDecimal totalAmount, java.util.List<OrderItem> orderItems) {
		this.orderId = orderId;
		this.branchId = branchId;
		this.tableId = tableId;
		this.staffId = staffId;
		this.status = status;
		this.totalAmount = totalAmount;
		this.orderItems = orderItems;
	}

	public Long getBranchId() {
		return branchId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public Long getStaffId() {
		return staffId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public Long getTableId() {
		return tableId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}