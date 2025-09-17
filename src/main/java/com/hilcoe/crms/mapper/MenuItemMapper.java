package com.hilcoe.crms.mapper;

import com.hilcoe.crms.entity.MenuItem;
import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {
    MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);

    MenuItemDTO toDTO(MenuItem entity);

    @Mapping(target = "menuItemId", ignore = true)
    @Mapping(target = "category", ignore = true)
    MenuItem toEntity(MenuItemDTO dto);

    MenuItemResponseDTO toResponseDTO(MenuItem entity);
}