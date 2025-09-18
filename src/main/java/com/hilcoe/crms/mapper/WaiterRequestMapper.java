package com.hilcoe.crms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.WaiterRequestDTO;
import com.hilcoe.crms.dto.WaiterRequestResponseDTO;
import com.hilcoe.crms.entity.WaiterRequest;

@Mapper(componentModel = "spring")
public interface WaiterRequestMapper {
	WaiterRequestMapper INSTANCE = Mappers.getMapper(WaiterRequestMapper.class);

	@Mapping(target = "branchId", ignore = true)
	@Mapping(target = "handledBy", ignore = true)
	@Mapping(target = "requestId", ignore = true)
	@Mapping(target = "status", ignore = true)
	WaiterRequest toEntity(WaiterRequestDTO dto);

	WaiterRequestResponseDTO toResponseDTO(WaiterRequest entity);
}