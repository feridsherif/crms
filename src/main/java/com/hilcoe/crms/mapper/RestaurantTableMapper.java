package com.hilcoe.crms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.RestaurantTableDTO;
import com.hilcoe.crms.dto.RestaurantTableResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.entity.RestaurantTable;

@Mapper(componentModel = "spring")
public interface RestaurantTableMapper {
	RestaurantTableMapper INSTANCE = Mappers.getMapper(RestaurantTableMapper.class);

	@Named("branchIdToBranch")
	default Branch branchIdToBranch(Long branchId) {
		if (branchId == null) {
			return null;
		}
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		return branch;
	}

	RestaurantTableDTO toDTO(RestaurantTable entity);

	@Mapping(target = "branch", source = "branchId", qualifiedByName = "branchIdToBranch")
	@Mapping(target = "tableId", ignore = true)
	RestaurantTable toEntity(RestaurantTableDTO dto);

	@Mapping(target = "branchId", source = "branch.branchId")
	RestaurantTableResponseDTO toResponseDTO(RestaurantTable entity);
}