package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.ShiftDTO;
import com.hilcoe.crms.dto.ShiftResponseDTO;
import com.hilcoe.crms.entity.Shift;
import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.entity.Branch;

public class ShiftMapper {
    public static Shift toEntity(ShiftDTO dto, Staff staff, Branch branch) {
        if (dto == null) return null;
        Shift shift = new Shift();
        shift.setStaff(staff);
        shift.setBranch(branch);
        shift.setStartTime(dto.getStartTime());
        shift.setEndTime(dto.getEndTime());
        return shift;
    }

    public static ShiftResponseDTO toResponseDTO(Shift shift) {
        if (shift == null) return null;
        ShiftResponseDTO dto = new ShiftResponseDTO();
        dto.setShiftId(shift.getShiftId());
        dto.setStaffId(shift.getStaff() != null ? shift.getStaff().getStaffId() : null);
        dto.setBranchId(shift.getBranch() != null ? shift.getBranch().getBranchId() : null);
        dto.setStartTime(shift.getStartTime());
        dto.setEndTime(shift.getEndTime());
        return dto;
    }

    public static void updateEntity(Shift shift, ShiftDTO dto, Staff staff, Branch branch) {
        if (staff != null) shift.setStaff(staff);
        if (branch != null) shift.setBranch(branch);
        if (dto.getStartTime() != null) shift.setStartTime(dto.getStartTime());
        if (dto.getEndTime() != null) shift.setEndTime(dto.getEndTime());
    }
}