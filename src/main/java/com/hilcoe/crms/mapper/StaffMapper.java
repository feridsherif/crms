package com.hilcoe.crms.mapper;

import com.hilcoe.crms.entity.Staff;
import com.hilcoe.crms.dto.StaffDTO;
import com.hilcoe.crms.dto.StaffResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    @Mapping(target = "staffId", ignore = true)
    @Mapping(target = "role", ignore = true)
    Staff toEntity(StaffDTO dto);

    StaffResponseDTO toResponseDTO(Staff entity);
}