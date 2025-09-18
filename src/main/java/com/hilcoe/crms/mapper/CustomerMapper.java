package com.hilcoe.crms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.CustomerDTO;
import com.hilcoe.crms.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	@Mapping(target = "userId", source = "user.userId")
	// name and phone are mapped automatically by MapStruct
	CustomerDTO toDTO(Customer entity);

	@Mapping(target = "user", ignore = true)
	// name and phone are mapped automatically by MapStruct
	Customer toEntity(CustomerDTO dto);
}