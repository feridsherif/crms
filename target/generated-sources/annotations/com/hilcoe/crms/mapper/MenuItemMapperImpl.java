package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import com.hilcoe.crms.entity.MenuItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T10:08:04+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class MenuItemMapperImpl implements MenuItemMapper {

    @Override
    public MenuItemDTO toDTO(MenuItem entity) {
        if ( entity == null ) {
            return null;
        }

        MenuItemDTO menuItemDTO = new MenuItemDTO();

        menuItemDTO.setDescription( entity.getDescription() );
        menuItemDTO.setIsAvailable( entity.getIsAvailable() );
        menuItemDTO.setName( entity.getName() );
        menuItemDTO.setPrice( entity.getPrice() );

        return menuItemDTO;
    }

    @Override
    public MenuItem toEntity(MenuItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MenuItem menuItem = new MenuItem();

        menuItem.setDescription( dto.getDescription() );
        menuItem.setIsAvailable( dto.getIsAvailable() );
        menuItem.setName( dto.getName() );
        menuItem.setPrice( dto.getPrice() );

        return menuItem;
    }

    @Override
    public MenuItemResponseDTO toResponseDTO(MenuItem entity) {
        if ( entity == null ) {
            return null;
        }

        MenuItemResponseDTO menuItemResponseDTO = new MenuItemResponseDTO();

        menuItemResponseDTO.setDescription( entity.getDescription() );
        menuItemResponseDTO.setIsAvailable( entity.getIsAvailable() );
        menuItemResponseDTO.setMenuItemId( entity.getMenuItemId() );
        menuItemResponseDTO.setName( entity.getName() );
        menuItemResponseDTO.setPrice( entity.getPrice() );

        return menuItemResponseDTO;
    }
}
