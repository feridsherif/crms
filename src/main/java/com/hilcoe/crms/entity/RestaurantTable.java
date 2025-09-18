package com.hilcoe.crms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {
	public static enum RestaurantTableStatus {
		AVAILABLE, OCCUPIED, OUT_OF_SERVICE, RESERVED
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", nullable = false)
	private Branch branch;

	@Column(name = "capacity", nullable = false)
	private Integer capacity;

	@Column(name = "location")
	private String location;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private RestaurantTableStatus status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "table_id")
	private Long tableId;

	@Column(name = "table_number", nullable = false)
	private String tableNumber;

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

	public Branch getBranch() {
		return branch;
	}

	public Long getBranchId() {
		return branch != null ? branch.getBranchId() : null;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public String getLocation() {
		return location;
	}

	public RestaurantTableStatus getStatus() {
		return status;
	}

	public Long getTableId() {
		return tableId;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setStatus(RestaurantTableStatus status) {
		this.status = status;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
}