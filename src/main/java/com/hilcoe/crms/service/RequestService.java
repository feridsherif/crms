package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.entity.WaiterRequest;
import com.hilcoe.crms.exception.WaiterRequestNotFound;
import com.hilcoe.crms.repository.WaiterRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {
    @Autowired
    private WaiterRequestRepository waiterRequestRepository;

    public WaiterRequestResponseDTO createRequest(WaiterRequestDTO dto, Long userId) {
        WaiterRequest request = new WaiterRequest();
        request.setTableId(dto.getTableId());
        request.setBranchId(dto.getBranchId());
        request.setRequestType(dto.getRequestType());
        request.setStatus(WaiterRequest.RequestStatus.NEW);
        // handledBy should be null on create
        WaiterRequest saved = waiterRequestRepository.save(request);
        return toDTO(saved);
    }

    public void acknowledgeRequest(Long id, Long waiterId) {
        WaiterRequest request = waiterRequestRepository.findById(id)
            .orElseThrow(() -> new WaiterRequestNotFound(id));
        request.setStatus(WaiterRequest.RequestStatus.ACKNOWLEDGED);
        // handledBy should not be set here
        waiterRequestRepository.save(request);
    }

    public void resolveRequest(Long id, Long waiterId) {
        WaiterRequest request = waiterRequestRepository.findById(id)
            .orElseThrow(() -> new WaiterRequestNotFound(id));
        request.setStatus(WaiterRequest.RequestStatus.RESOLVED);
        request.setHandledBy(waiterId);
        waiterRequestRepository.save(request);
    }

    public List<WaiterRequestResponseDTO> getRequests() {
        return waiterRequestRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
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