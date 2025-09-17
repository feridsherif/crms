package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

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
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        staff.setRoleId(roleId);
        staffRepository.save(staff);
    }

    public List<StaffResponseDTO> getStaff() {
        return staffRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
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