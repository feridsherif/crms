package com.hilcoe.crms.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.repository.RoleRepository;
import com.hilcoe.crms.repository.StaffRepository;

@Service
public class StaffService {
	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StaffRepository staffRepository;

	public StaffResponseDTO addStaff(StaffDTO dto, Long userId) {
		Staff staff = new Staff();
		staff.setUserId(dto.getUserId());
		staff.setRoleId(dto.getRoleId());
		staff.setContact(dto.getContact());
		Staff saved = staffRepository.save(staff);
		auditLogService.log(userId, "CREATE", "Staff", saved.getStaffId(), saved);
		// Notify user of role assignment
		try {
			String dataJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(saved);
			notificationService.sendToUser(saved.getUserId(), "Role Assigned", "You have been assigned a new role.",
					dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
		return toResponseDTO(saved);
	}

	public void assignRole(Long staffId, Long roleId, Long userId) {
		Staff staff = staffRepository.findById(staffId).orElseThrow(
				() -> new com.hilcoe.crms.exception.StaffNotFoundException("Staff not found with id: " + staffId));
		Role role = roleRepository.findById(roleId).orElseThrow(
				() -> new com.hilcoe.crms.exception.RoleNotFoundException("Role not found with id: " + roleId));
		Staff before = new Staff();
		before.setStaffId(staff.getStaffId());
		before.setUserId(staff.getUserId());
		before.setRoleId(staff.getRoleId());
		before.setContact(staff.getContact());
		staff.setRole(role);
		Staff updated = staffRepository.save(staff);
		HashMap<String, Object> data = new HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Staff", updated.getStaffId(), data);
		// Notify user of role assignment
		try {
			String dataJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(updated);
			notificationService.sendToUser(updated.getUserId(), "Role Assigned",
					"You have been assigned a new role: " + role.getName(), dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
	}

	public List<StaffResponseDTO> getStaff() {
		return staffRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
	}

	public StaffResponseDTO getStaffById(Long id) {
		Staff staff = staffRepository.findById(id).orElseThrow(
				() -> new com.hilcoe.crms.exception.StaffNotFoundException("Staff not found with id: " + id));
		return toResponseDTO(staff);
	}

	public com.hilcoe.crms.dto.PaginatedResponseDTO<StaffResponseDTO> getStaffPaginated(Pageable pageable) {
		Page<Staff> page = staffRepository.findAll(pageable);
		java.util.List<StaffResponseDTO> content = page.getContent().stream().map(this::toResponseDTO)
				.collect(java.util.stream.Collectors.toList());
		int pageNum = page.getNumber();
		int pageSize = page.getSize();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		boolean hasNext = page.hasNext();
		boolean hasPrevious = page.hasPrevious();
		String sort = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new com.hilcoe.crms.dto.PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages,
				hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public com.hilcoe.crms.dto.PaginatedResponseDTO<StaffResponseDTO> getStaffPaginatedAdvanced(Long userId,
			Long roleId, String contact, int page, int size, String sortBy, String direction) {
		org.springframework.data.domain.Sort sort = direction.equalsIgnoreCase("desc")
				? org.springframework.data.domain.Sort.by(sortBy).descending()
				: org.springframework.data.domain.Sort.by(sortBy).ascending();
		org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size,
				sort);
		org.springframework.data.domain.Page<Staff> staffPage = staffRepository.findAll(pageable);
		java.util.List<Staff> filtered = staffPage.getContent().stream()
				.filter(s -> userId == null || s.getUserId().equals(userId))
				.filter(s -> roleId == null || s.getRoleId().equals(roleId))
				.filter(s -> contact == null
						|| (s.getContact() != null && s.getContact().toLowerCase().contains(contact.toLowerCase())))
				.collect(java.util.stream.Collectors.toList());
		java.util.List<StaffResponseDTO> content = filtered.stream().map(this::toResponseDTO)
				.collect(java.util.stream.Collectors.toList());
		int pageNum = staffPage.getNumber();
		int pageSize = staffPage.getSize();
		long totalElements = staffPage.getTotalElements();
		int totalPages = staffPage.getTotalPages();
		boolean hasNext = staffPage.hasNext();
		boolean hasPrevious = staffPage.hasPrevious();
		String sortStr = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new com.hilcoe.crms.dto.PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages,
				hasNext, hasPrevious, sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl,
				previousPageUrl);
	}

	public void removeStaff(Long id, Long userId) {
		Staff staff = staffRepository.findById(id).orElseThrow(
				() -> new com.hilcoe.crms.exception.StaffNotFoundException("Staff not found with id: " + id));
		auditLogService.log(userId, "DELETE", "Staff", staff.getStaffId(), staff);
		staffRepository.deleteById(id);
	}

	private StaffResponseDTO toResponseDTO(Staff staff) {
		return new StaffResponseDTO(staff.getStaffId(), staff.getUserId(), staff.getRoleId(), staff.getContact());
	}

	public StaffResponseDTO updateStaff(Long id, StaffDTO dto, Long userId) {
		Staff staff = staffRepository.findById(id).orElseThrow(
				() -> new com.hilcoe.crms.exception.StaffNotFoundException("Staff not found with id: " + id));
		Staff before = new Staff();
		before.setStaffId(staff.getStaffId());
		before.setUserId(staff.getUserId());
		before.setRoleId(staff.getRoleId());
		before.setContact(staff.getContact());
		staff.setUserId(dto.getUserId());
		staff.setRoleId(dto.getRoleId());
		staff.setContact(dto.getContact());
		Staff updated = staffRepository.save(staff);
		HashMap<String, Object> data = new HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Staff", updated.getStaffId(), data);
		// Notify user if role changed
		try {
			String dataJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(updated);
			notificationService.sendToUser(updated.getUserId(), "Role Updated", "Your role has been updated.",
					dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
		return toResponseDTO(updated);
	}
}