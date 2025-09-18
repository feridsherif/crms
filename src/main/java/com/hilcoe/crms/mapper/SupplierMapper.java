package com.hilcoe.crms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import com.hilcoe.crms.entity.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
	SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

	@Mapping(target = "supplierId", ignore = true)
	Supplier toEntity(SupplierDTO dto);

	SupplierResponseDTO toResponseDTO(Supplier entity);
}