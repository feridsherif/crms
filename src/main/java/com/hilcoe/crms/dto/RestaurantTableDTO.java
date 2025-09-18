package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RestaurantTableDTO {
	@NotNull
	private Long branchId;
	@NotNull
	private Integer capacity;
	private String location;
	private Long tableId;
	@NotBlank
	private String tableNumber;

	public RestaurantTableDTO() {
	}

	public RestaurantTableDTO(Long tableId, Long branchId, String tableNumber, Integer capacity, String location) {
		this.tableId = tableId;
		this.branchId = branchId;
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.location = location;
	}

	public Long getBranchId() {
		return branchId;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public String getLocation() {
		return location;
	}

	public Long getTableId() {
		return tableId;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
}