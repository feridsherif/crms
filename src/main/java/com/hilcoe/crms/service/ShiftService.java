package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.ShiftDTO;
import com.hilcoe.crms.dto.ShiftResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.PaginationDTO;
import com.hilcoe.crms.entity.Shift;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.exception.ShiftNotFoundException;
import com.hilcoe.crms.mapper.ShiftMapper;
import com.hilcoe.crms.repository.ShiftRepository;
import com.hilcoe.crms.repository.StaffRepository;
import com.hilcoe.crms.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private BranchRepository branchRepository;

    public ShiftResponseDTO createShift(ShiftDTO dto) {
        Staff staff = staffRepository.findById(dto.getStaffId())
            .orElseThrow(() -> new ShiftNotFoundException("Staff not found with id: " + dto.getStaffId()));
        Branch branch = branchRepository.findById(dto.getBranchId())
            .orElseThrow(() -> new ShiftNotFoundException("Branch not found with id: " + dto.getBranchId()));
        Shift shift = ShiftMapper.toEntity(dto, staff, branch);
        Shift saved = shiftRepository.save(shift);
        return ShiftMapper.toResponseDTO(saved);
    }

    public ShiftResponseDTO getShift(Long id) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new ShiftNotFoundException("Shift not found with id: " + id));
        return ShiftMapper.toResponseDTO(shift);
    }

    public PaginatedResponseDTO<ShiftResponseDTO> getShifts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Shift> shiftPage = shiftRepository.findAll(pageable);
        List<ShiftResponseDTO> content = shiftPage.getContent().stream()
                .map(ShiftMapper::toResponseDTO)
                .collect(Collectors.toList());
        return new PaginatedResponseDTO<>(content, shiftPage.getNumber(), shiftPage.getSize(), shiftPage.getTotalElements(), shiftPage.getTotalPages());
    }

    public PaginatedResponseDTO<ShiftResponseDTO> getShiftsPaginated(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Shift> shiftPage = shiftRepository.findAll(pageable);
        List<ShiftResponseDTO> content = shiftPage.getContent().stream()
                .map(ShiftMapper::toResponseDTO)
                .collect(Collectors.toList());
        return new PaginatedResponseDTO<>(content, shiftPage.getNumber(), shiftPage.getSize(), shiftPage.getTotalElements(), shiftPage.getTotalPages());
    }

    public ShiftResponseDTO updateShift(Long id, ShiftDTO dto) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new ShiftNotFoundException("Shift not found with id: " + id));
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
        return ShiftMapper.toResponseDTO(updated);
    }

    public void deleteShift(Long id) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new ShiftNotFoundException("Shift not found with id: " + id));
        shiftRepository.delete(shift);
    }

    public List<ShiftResponseDTO> getAllShifts() {
        return shiftRepository.findAll().stream()
            .map(ShiftMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
}