package com.hilcoe.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.ShiftDTO;
import com.hilcoe.crms.dto.ShiftResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.Shift;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.exception.ShiftNotFoundException;
import com.hilcoe.crms.mapper.ShiftMapper;
import com.hilcoe.crms.repository.BranchRepository;
import com.hilcoe.crms.repository.ShiftRepository;
import com.hilcoe.crms.repository.StaffRepository;

@Service
public class ShiftService {
	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private ShiftRepository shiftRepository;
	@Autowired
	private StaffRepository staffRepository;

	public ShiftResponseDTO createShift(ShiftDTO dto, Long userId) {
		Staff staff = staffRepository.findById(dto.getStaffId())
				.orElseThrow(() -> new ShiftNotFoundException("Staff not found with id: " + dto.getStaffId()));
		Branch branch = branchRepository.findById(dto.getBranchId())
				.orElseThrow(() -> new ShiftNotFoundException("Branch not found with id: " + dto.getBranchId()));
		Shift shift = ShiftMapper.toEntity(dto, staff, branch);
		Shift saved = shiftRepository.save(shift);
		auditLogService.log(userId, "CREATE", "Shift", saved.getShiftId(), saved);
		// Notify staff of new shift assignment
		try {
			String dataJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(saved);
			notificationService.sendToUser(staff.getUserId(), "Shift Assigned", "You have been assigned a new shift.",
					dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
		return ShiftMapper.toResponseDTO(saved);
	}

	public void deleteShift(Long id, Long userId) {
		Shift shift = shiftRepository.findById(id)
				.orElseThrow(() -> new ShiftNotFoundException("Shift not found with id: " + id));
		auditLogService.log(userId, "DELETE", "Shift", shift.getShiftId(), shift);
		shiftRepository.delete(shift);
	}

	public List<ShiftResponseDTO> getAllShifts() {
		return shiftRepository.findAll().stream().map(ShiftMapper::toResponseDTO).collect(Collectors.toList());
	}

	public ShiftResponseDTO getShift(Long id) {
		Shift shift = shiftRepository.findById(id)
				.orElseThrow(() -> new ShiftNotFoundException("Shift not found with id: " + id));
		return ShiftMapper.toResponseDTO(shift);
	}

	public PaginatedResponseDTO<ShiftResponseDTO> getShifts(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Shift> shiftPage = shiftRepository.findAll(pageable);
		List<ShiftResponseDTO> content = shiftPage.getContent().stream().map(ShiftMapper::toResponseDTO)
				.collect(Collectors.toList());
		return new PaginatedResponseDTO<>(content, shiftPage.getNumber(), shiftPage.getSize(),
				shiftPage.getTotalElements(), shiftPage.getTotalPages());
	}

	public PaginatedResponseDTO<ShiftResponseDTO> getShiftsPaginated(int page, int size, String sortBy,
			String direction) {
		Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Shift> shiftPage = shiftRepository.findAll(pageable);
		List<ShiftResponseDTO> content = shiftPage.getContent().stream().map(ShiftMapper::toResponseDTO)
				.collect(Collectors.toList());
		return new PaginatedResponseDTO<>(content, shiftPage.getNumber(), shiftPage.getSize(),
				shiftPage.getTotalElements(), shiftPage.getTotalPages());
	}

	public PaginatedResponseDTO<ShiftResponseDTO> getShiftsPaginatedAdvanced(Long staffId, Long branchId,
			String minStartTime, String maxEndTime, int page, int size, String sortBy, String direction) {
		org.springframework.data.domain.Sort sort = direction.equalsIgnoreCase("desc")
				? org.springframework.data.domain.Sort.by(sortBy).descending()
				: org.springframework.data.domain.Sort.by(sortBy).ascending();
		org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size,
				sort);
		org.springframework.data.domain.Page<Shift> shiftPage = shiftRepository.findAll(pageable);
		java.time.LocalDateTime minStart = minStartTime != null ? java.time.LocalDateTime.parse(minStartTime) : null;
		java.time.LocalDateTime maxEnd = maxEndTime != null ? java.time.LocalDateTime.parse(maxEndTime) : null;
		java.util.List<Shift> filtered = shiftPage.getContent().stream()
				.filter(s -> staffId == null || (s.getStaff() != null && s.getStaff().getStaffId().equals(staffId)))
				.filter(s -> branchId == null
						|| (s.getBranch() != null && s.getBranch().getBranchId().equals(branchId)))
				.filter(s -> minStart == null || (s.getStartTime() != null && !s.getStartTime().isBefore(minStart)))
				.filter(s -> maxEnd == null || (s.getEndTime() != null && !s.getEndTime().isAfter(maxEnd)))
				.collect(java.util.stream.Collectors.toList());
		java.util.List<ShiftResponseDTO> content = filtered.stream().map(ShiftMapper::toResponseDTO)
				.collect(java.util.stream.Collectors.toList());
		int pageNum = shiftPage.getNumber();
		int pageSize = shiftPage.getSize();
		long totalElements = shiftPage.getTotalElements();
		int totalPages = shiftPage.getTotalPages();
		boolean hasNext = shiftPage.hasNext();
		boolean hasPrevious = shiftPage.hasPrevious();
		String sortStr = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public ShiftResponseDTO updateShift(Long id, ShiftDTO dto, Long userId) {
		Shift shift = shiftRepository.findById(id)
				.orElseThrow(() -> new ShiftNotFoundException("Shift not found with id: " + id));
		Shift before = new Shift();
		before.setShiftId(shift.getShiftId());
		before.setStaff(shift.getStaff());
		before.setBranch(shift.getBranch());
		before.setStartTime(shift.getStartTime());
		before.setEndTime(shift.getEndTime());
		Staff staff = null;
		Branch branch = null;
		if (dto.getStaffId() != null) {
			staff = staffRepository.findById(dto.getStaffId())
					.orElseThrow(() -> new ShiftNotFoundException("Staff not found with id: " + dto.getStaffId()));
		}
		if (dto.getBranchId() != null) {
			branch = branchRepository.findById(dto.getBranchId())
					.orElseThrow(() -> new ShiftNotFoundException("Branch not found with id: " + dto.getBranchId()));
		}
		ShiftMapper.updateEntity(shift, dto, staff, branch);
		Shift updated = shiftRepository.save(shift);
		java.util.HashMap<String, Object> data = new java.util.HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Shift", updated.getShiftId(), data);
		return ShiftMapper.toResponseDTO(updated);
	}
}