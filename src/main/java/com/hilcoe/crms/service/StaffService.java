package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.repository.RoleRepository;
import com.hilcoe.crms.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    public StaffResponseDTO addStaff(StaffDTO dto) {
        Staff staff = new Staff();
        staff.setUserId(dto.getUserId());
        staff.setRoleId(dto.getRoleId());
        staff.setContact(dto.getContact());
        Staff saved = staffRepository.save(staff);
        return toResponseDTO(saved);
    }

    public StaffResponseDTO updateStaff(Long id, StaffDTO dto) {
        Staff staff = staffRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.StaffNotFoundException("Staff not found with id: " + id));
        staff.setUserId(dto.getUserId());
        staff.setRoleId(dto.getRoleId());
        staff.setContact(dto.getContact());
        Staff updated = staffRepository.save(staff);
        return toResponseDTO(updated);
    }

    public void removeStaff(Long id) {
        Staff staff = staffRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.StaffNotFoundException("Staff not found with id: " + id));
        staffRepository.deleteById(id);
    }

    public void assignRole(Long staffId, Long roleId) {
        Staff staff = staffRepository.findById(staffId)
            .orElseThrow(() -> new com.hilcoe.crms.exception.StaffNotFoundException("Staff not found with id: " + staffId));
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new com.hilcoe.crms.exception.RoleNotFoundException("Role not found with id: " + roleId));
        staff.setRole(role);
        staffRepository.save(staff);
    }

    public List<StaffResponseDTO> getStaff() {
        return staffRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public StaffResponseDTO getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.StaffNotFoundException("Staff not found with id: " + id));
        return toResponseDTO(staff);
    }

    public com.hilcoe.crms.dto.PaginatedResponseDTO<StaffResponseDTO> getStaffPaginated(Pageable pageable) {
        Page<Staff> page = staffRepository.findAll(pageable);
        java.util.List<StaffResponseDTO> content = page.getContent().stream().map(this::toResponseDTO).collect(java.util.stream.Collectors.toList());
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
        return new com.hilcoe.crms.dto.PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

    private StaffResponseDTO toResponseDTO(Staff staff) {
        return new StaffResponseDTO(
            staff.getStaffId(),
            staff.getUserId(),
            staff.getRoleId(),
            staff.getContact()
        );
    }
}