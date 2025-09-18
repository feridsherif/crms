package com.hilcoe.crms.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.UserDTO;
import com.hilcoe.crms.dto.UserResponseDTO;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Named("mapIdsToRoles")
	default Set<Role> mapIdsToRoles(Set<Long> roleIds) {
		if (roleIds == null) {
			return null;
		}
		return roleIds.stream().map(id -> {
			Role role = new Role();
			role.setRoleId(id);
			return role;
		}).collect(Collectors.toSet());
	}

	@Named("mapRolesToIds")
	default Set<Long> mapRolesToIds(Set<Role> roles) {
		if (roles == null) {
			return null;
		}
		return roles.stream().map(Role::getRoleId).collect(Collectors.toSet());
	}

	@Mapping(source = "roles", target = "roleIds", qualifiedByName = "mapRolesToIds")
	UserDTO toDTO(User entity);

	@Mapping(source = "roleIds", target = "roles", qualifiedByName = "mapIdsToRoles")
	@Mapping(target = "userId", ignore = true)
	User toEntity(UserDTO dto);

	@Mapping(target = "roleIds", source = "roles", qualifiedByName = "mapRolesToIds")
	UserResponseDTO toResponseDTO(User entity);
}