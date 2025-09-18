package com.hilcoe.crms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "waiter_requests")
public class WaiterRequest {
	// Enum for request status
	public static enum RequestStatus {
		ACKNOWLEDGED, NEW, RESOLVED
	}

	// Enum for request type
	public static enum RequestType {
		CALL_WAITER, CLEAN_TABLE, ORDER_ASSISTANCE, OTHER, REQUEST_BILL
	}

	@Column(name = "branch_id", nullable = false)
	private Long branchId;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "handled_by")
	private Long handledBy;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Long requestId;

	@Enumerated(EnumType.STRING)
	@Column(name = "request_type", nullable = false)
	private RequestType requestType;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private RequestStatus status;

	@Column(name = "table_id", nullable = false)
	private Long tableId;

	public WaiterRequest() {
	}

	public WaiterRequest(Long requestId, Long tableId, Long branchId, RequestType requestType, RequestStatus status,
			Long handledBy, Long customerId) {
		this.requestId = requestId;
		this.tableId = tableId;
		this.branchId = branchId;
		this.requestType = requestType;
		this.status = status;
		this.handledBy = handledBy;
		this.customerId = customerId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Long getHandledBy() {
		return handledBy;
	}

	public Long getRequestId() {
		return requestId;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setHandledBy(Long handledBy) {
		this.handledBy = handledBy;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
}