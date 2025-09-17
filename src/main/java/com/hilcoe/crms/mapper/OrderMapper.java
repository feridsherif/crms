package com.hilcoe.crms.mapper;

import com.hilcoe.crms.entity.Order;
import com.hilcoe.crms.dto.OrderCreateDTO;
import com.hilcoe.crms.dto.OrderResponseDTO;
import com.hilcoe.crms.dto.OrderFullResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "staffId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(OrderCreateDTO dto);

    OrderResponseDTO toResponseDTO(Order entity);
    OrderFullResponseDTO toFullResponseDTO(Order entity);
}