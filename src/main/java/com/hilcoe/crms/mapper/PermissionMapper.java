package com.hilcoe.crms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.PermissionDTO;
import com.hilcoe.crms.dto.PermissionResponseDTO;
import com.hilcoe.crms.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
	PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

	PermissionDTO toDTO(Permission entity);

	@Mapping(target = "permissionId", ignore = true)
	Permission toEntity(PermissionDTO dto);

	PermissionResponseDTO toResponseDTO(Permission entity);
}