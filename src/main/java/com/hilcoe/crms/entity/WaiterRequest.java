package com.hilcoe.crms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "waiter_requests")
public class WaiterRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Long requestId;

	@Column(name = "table_id", nullable = false)
	private Long tableId;

	@Column(name = "branch_id", nullable = false)
	private Long branchId;

	@Enumerated(EnumType.STRING)
	@Column(
		name = "request_type",
		nullable = false
	)
	private RequestType requestType;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private RequestStatus status;

	@Column(name = "handled_by")
	private Long handledBy;

	public WaiterRequest() {
	}

	public WaiterRequest(Long requestId, Long tableId, Long branchId, RequestType requestType, RequestStatus status,
			Long handledBy) {
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

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public Long getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(Long handledBy) {
		this.handledBy = handledBy;
	}

	// Enum for request status
	public static enum RequestStatus {
		NEW, ACKNOWLEDGED, RESOLVED
	}

	// Enum for request type
	public static enum RequestType {
		CALL_WAITER, REQUEST_BILL, ORDER_ASSISTANCE, CLEAN_TABLE, OTHER
	}
}