package com.hilcoe.crms.mapper;

import com.hilcoe.crms.entity.InventoryItem;
import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {
    InventoryItemMapper INSTANCE = Mappers.getMapper(InventoryItemMapper.class);

    InventoryItemDTO toDTO(InventoryItem entity);

    @Mapping(target = "inventoryItemId", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    InventoryItem toEntity(InventoryItemDTO dto);

    InventoryItemResponseDTO toResponseDTO(InventoryItem entity);
}