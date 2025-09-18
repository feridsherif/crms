package com.hilcoe.crms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.BranchDTO;
import com.hilcoe.crms.dto.BranchResponseDTO;
import com.hilcoe.crms.entity.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {
	BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

	BranchDTO toDTO(Branch branch);

	@Mapping(target = "branchId", ignore = true)
	Branch toEntity(BranchDTO branchDTO);

	BranchResponseDTO toResponseDTO(Branch branch);
}