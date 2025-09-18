package com.hilcoe.crms.dto;

import com.hilcoe.crms.entity.RestaurantTable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RestaurantTableResponseDTO {
	@NotNull
	private Long branchId;
	@NotNull
	private Integer capacity;
	private String location;
	@NotBlank
	private RestaurantTable.RestaurantTableStatus status;
	@NotNull
	private Long tableId;
	@NotBlank
	private String tableNumber;

	public RestaurantTableResponseDTO() {
	}

	public RestaurantTableResponseDTO(Long tableId, Long branchId, String tableNumber, Integer capacity,
			String location, RestaurantTable.RestaurantTableStatus status) {
		this.tableId = tableId;
		this.branchId = branchId;
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.location = location;
		this.status = status;
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

	public RestaurantTable.RestaurantTableStatus getStatus() {
		return status;
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

	public void setStatus(RestaurantTable.RestaurantTableStatus status) {
		this.status = status;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
}