package com.hilcoe.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.entity.WaiterRequest;
import com.hilcoe.crms.exception.WaiterRequestNotFound;
import com.hilcoe.crms.repository.WaiterRequestRepository;

@Service
public class RequestService {
	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private NotificationService notificationService;

	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private WaiterRequestRepository waiterRequestRepository;

	public void acknowledgeRequest(Long id, Long waiterId) {
		WaiterRequest request = waiterRequestRepository.findById(id).orElseThrow(() -> new WaiterRequestNotFound(id));
		WaiterRequest before = new WaiterRequest();
		before.setRequestId(request.getRequestId());
		before.setTableId(request.getTableId());
		before.setBranchId(request.getBranchId());
		before.setRequestType(request.getRequestType());
		before.setStatus(request.getStatus());
		before.setHandledBy(request.getHandledBy());
		request.setStatus(WaiterRequest.RequestStatus.ACKNOWLEDGED);
		request.setHandledBy(waiterId);
		WaiterRequest updated = waiterRequestRepository.save(request);
		java.util.HashMap<String, Object> data = new java.util.HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(waiterId, "UPDATE", "WaiterRequest", updated.getRequestId(), data);
		// Notify customer (if possible)
		try {
			String dataJson = objectMapper.writeValueAsString(updated);
			// If customerId is available, notify them
			if (updated.getCustomerId() != null) {
				notificationService.sendToUser(updated.getCustomerId(), "Waiter Request Acknowledged",
						"Your request has been acknowledged by a waiter.", dataJson);
			}
		} catch (Exception e) {
			/* log or ignore */ }
	}

	public WaiterRequestResponseDTO createRequest(WaiterRequestDTO dto, Long userId) {
		WaiterRequest request = new WaiterRequest();
		request.setTableId(dto.getTableId());
		request.setBranchId(dto.getBranchId());
		request.setRequestType(dto.getRequestType());
		request.setStatus(WaiterRequest.RequestStatus.NEW);
		request.setCustomerId(dto.getCustomerId());
		// handledBy should be null on create
		WaiterRequest saved = waiterRequestRepository.save(request);
		auditLogService.log(userId, "CREATE", "WaiterRequest", saved.getRequestId(), saved);
		// Notify waiter (role: WAITER)
		try {
			String dataJson = objectMapper.writeValueAsString(saved);
			notificationService.sendToRole("WAITER", "New Waiter Request", "A new waiter request has been created.",
					dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
		return toDTO(saved);
	}

	public List<WaiterRequestResponseDTO> getRequests() {
		return waiterRequestRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	public void resolveRequest(Long id, Long waiterId) {
		WaiterRequest request = waiterRequestRepository.findById(id).orElseThrow(() -> new WaiterRequestNotFound(id));
		WaiterRequest before = new WaiterRequest();
		before.setRequestId(request.getRequestId());
		before.setTableId(request.getTableId());
		before.setBranchId(request.getBranchId());
		before.setRequestType(request.getRequestType());
		before.setStatus(request.getStatus());
		before.setHandledBy(request.getHandledBy());
		request.setStatus(WaiterRequest.RequestStatus.RESOLVED);
		request.setHandledBy(waiterId);
		WaiterRequest updated = waiterRequestRepository.save(request);
		java.util.HashMap<String, Object> data = new java.util.HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(waiterId, "UPDATE", "WaiterRequest", updated.getRequestId(), data);
		// Notify customer (if possible)
		try {
			String dataJson = objectMapper.writeValueAsString(updated);
			if (updated.getCustomerId() != null) {
				notificationService.sendToUser(updated.getCustomerId(), "Waiter Request Resolved",
						"Your request has been resolved.", dataJson);
			}
		} catch (Exception e) {
			/* log or ignore */ }
	}

	private WaiterRequestResponseDTO toDTO(WaiterRequest request) {
		WaiterRequestResponseDTO dto = new WaiterRequestResponseDTO();
		dto.setRequestId(request.getRequestId());
		dto.setTableId(request.getTableId());
		dto.setBranchId(request.getBranchId());
		dto.setRequestType(request.getRequestType());
		dto.setStatus(request.getStatus().name());
		dto.setHandledBy(request.getHandledBy());
		return dto;
	}
}