package com.hilcoe.crms.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "branch_id", nullable = false)
	private Long branchId;

	@Column(name = "table_id", nullable = false)
	private Long tableId;

	@Column(name = "staff_id", nullable = false)
	private Long staffId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private OrderStatus status;

	@Column(name = "total_amount", nullable = false)
	private BigDecimal totalAmount;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	// Enum for order status
	public static enum OrderStatus {
		CREATED, IN_PROGRESS, COMPLETED, CANCELLED
	}
}