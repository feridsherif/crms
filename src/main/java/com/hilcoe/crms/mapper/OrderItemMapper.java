package com.hilcoe.crms.mapper;

import com.hilcoe.crms.entity.OrderItem;
import com.hilcoe.crms.dto.OrderItemDTO;
import com.hilcoe.crms.dto.OrderItemResponseDTO;
import com.hilcoe.crms.entity.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(source = "menuItem.menuItemId", target = "menuItemId")
    OrderItemDTO toDTO(OrderItem entity);

    @Mapping(source = "menuItemId", target = "menuItem", qualifiedByName = "mapIdToMenuItem")
    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    OrderItem toEntity(OrderItemDTO dto);

    @Mapping(source = "menuItem.menuItemId", target = "menuItemId")
    OrderItemResponseDTO toResponseDTO(OrderItem entity);

    default Long mapMenuItemToId(MenuItem menuItem) {
        return menuItem != null ? menuItem.getMenuItemId() : null;
    }

    @Named("mapIdToMenuItem")
    default MenuItem mapIdToMenuItem(Long menuItemId) {
        if (menuItemId == null) return null;
        MenuItem item = new MenuItem();
        item.setMenuItemId(menuItemId);
        return item;
    }
}