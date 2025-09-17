package com.hilcoe.crms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "table_id")
	private Long tableId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", nullable = false)
	private Branch branch;

	@Column(name = "table_number", nullable = false)
	private String tableNumber;

	@Column(name = "capacity", nullable = false)
	private Integer capacity;

	@Column(name = "location")
	private String location;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private RestaurantTableStatus status;

	public static enum RestaurantTableStatus {
		AVAILABLE,
		OCCUPIED,
		RESERVED,
		OUT_OF_SERVICE
	}

	public RestaurantTable() {
		this.status = RestaurantTableStatus.AVAILABLE;
	}

	public RestaurantTable(Long tableId, Branch branch, String tableNumber, Integer capacity, String location) {
		this.tableId = tableId;
		this.branch = branch;
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.location = location;
		this.status = RestaurantTableStatus.AVAILABLE;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Long getBranchId() {
		return branch != null ? branch.getBranchId() : null;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public RestaurantTableStatus getStatus() {
		return status;
	}

	public void setStatus(RestaurantTableStatus status) {
		this.status = status;
	}
}