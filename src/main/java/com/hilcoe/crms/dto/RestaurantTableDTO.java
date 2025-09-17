package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RestaurantTableDTO {
    private Long tableId;
    @NotNull
    private Long branchId;
    @NotBlank
    private String tableNumber;
    @NotNull
    private Integer capacity;
    private String location;

    public RestaurantTableDTO() {}

    public RestaurantTableDTO(Long tableId, Long branchId, String tableNumber, Integer capacity, String location) {
        this.tableId = tableId;
        this.branchId = branchId;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.location = location;
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
}