package com.hilcoe.crms.mapper;

import com.hilcoe.crms.entity.Supplier;
import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
	SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

	@Mapping(target = "supplierId", ignore = true)
	Supplier toEntity(SupplierDTO dto);

	SupplierResponseDTO toResponseDTO(Supplier entity);
}