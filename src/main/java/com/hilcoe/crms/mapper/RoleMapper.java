package com.hilcoe.crms.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.dto.RoleResponseDTO;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	RoleDTO toDTO(Role entity);

	Role toEntity(RoleDTO dto);

	default RoleResponseDTO toResponseDTO(Role entity) {
		Set<Long> permissionIds = entity.getPermissions() == null ? null
				: entity.getPermissions().stream().map(Permission::getPermissionId).collect(Collectors.toSet());
		Set<String> permissionNames = entity.getPermissions() == null ? null
				: entity.getPermissions().stream().map(Permission::getName).collect(Collectors.toSet());
		return new RoleResponseDTO(entity.getRoleId(), entity.getName(), entity.getDescription(), permissionIds,
				permissionNames);
	}
}