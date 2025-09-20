package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import com.hilcoe.crms.entity.Staff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T10:08:04+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public Staff toEntity(StaffDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setContact( dto.getContact() );
        staff.setRoleId( dto.getRoleId() );
        staff.setUserId( dto.getUserId() );

        return staff;
    }

    @Override
    public StaffResponseDTO toResponseDTO(Staff entity) {
        if ( entity == null ) {
            return null;
        }

        StaffResponseDTO staffResponseDTO = new StaffResponseDTO();

        staffResponseDTO.setContact( entity.getContact() );
        staffResponseDTO.setRoleId( entity.getRoleId() );
        staffResponseDTO.setStaffId( entity.getStaffId() );
        staffResponseDTO.setUserId( entity.getUserId() );

        return staffResponseDTO;
    }
}
