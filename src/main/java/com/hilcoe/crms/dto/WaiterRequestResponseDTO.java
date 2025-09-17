package com.hilcoe.crms.dto;

import com.hilcoe.crms.entity.WaiterRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class WaiterRequestResponseDTO {
    @NotNull
    private Long requestId;
    @NotNull
    private Long tableId;
    @NotNull
    private Long branchId;
    @NotNull
    private WaiterRequest.RequestType requestType;
    @NotBlank
    private String status;
    private Long handledBy;

    public WaiterRequestResponseDTO() {
		super();
	}

	public WaiterRequestResponseDTO(@NotNull Long requestId, @NotNull Long tableId, @NotNull Long branchId,
            @NotNull WaiterRequest.RequestType requestType, @NotBlank String status, Long handledBy) {
        super();
        this.requestId = requestId;
        this.tableId = tableId;
        this.branchId = branchId;
        this.requestType = requestType;
        this.status = status;
        this.handledBy = handledBy;
    }

	public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public WaiterRequest.RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(WaiterRequest.RequestType requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(Long handledBy) {
        this.handledBy = handledBy;
    }
}