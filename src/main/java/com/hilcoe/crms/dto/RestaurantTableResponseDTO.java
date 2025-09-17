package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import com.hilcoe.crms.entity.RestaurantTable;

public class RestaurantTableResponseDTO {
    @NotNull
    private Long tableId;
    @NotNull
    private Long branchId;
    @NotBlank
    private String tableNumber;
    @NotNull
    private Integer capacity;
    private String location;
    @NotBlank
    private RestaurantTable.RestaurantTableStatus status;

    public RestaurantTableResponseDTO() {}

    public RestaurantTableResponseDTO(Long tableId, Long branchId, String tableNumber, Integer capacity, String location, RestaurantTable.RestaurantTableStatus status) {
        this.tableId = tableId;
        this.branchId = branchId;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.location = location;
        this.status = status;
    }

    public Long getTableId() { return tableId; }
    public void setTableId(Long tableId) { this.tableId = tableId; }
    public Long getBranchId() { return branchId; }
    public void setBranchId(Long branchId) { this.branchId = branchId; }
    public String getTableNumber() { return tableNumber; }
    public void setTableNumber(String tableNumber) { this.tableNumber = tableNumber; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public RestaurantTable.RestaurantTableStatus getStatus() { return status; }
    public void setStatus(RestaurantTable.RestaurantTableStatus status) { this.status = status; }
}