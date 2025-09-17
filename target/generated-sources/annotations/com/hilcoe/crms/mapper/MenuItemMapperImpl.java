package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.MenuItemDTO;
import com.hilcoe.crms.dto.MenuItemResponseDTO;
import com.hilcoe.crms.entity.MenuItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T09:18:42+0300",
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

        menuItemDTO.setName( entity.getName() );
        menuItemDTO.setDescription( entity.getDescription() );
        menuItemDTO.setPrice( entity.getPrice() );
        menuItemDTO.setIsAvailable( entity.getIsAvailable() );

        return menuItemDTO;
    }

    @Override
    public MenuItem toEntity(MenuItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MenuItem menuItem = new MenuItem();

        menuItem.setName( dto.getName() );
        menuItem.setDescription( dto.getDescription() );
        menuItem.setPrice( dto.getPrice() );
        menuItem.setIsAvailable( dto.getIsAvailable() );

        return menuItem;
    }

    @Override
    public MenuItemResponseDTO toResponseDTO(MenuItem entity) {
        if ( entity == null ) {
            return null;
        }

        MenuItemResponseDTO menuItemResponseDTO = new MenuItemResponseDTO();

        menuItemResponseDTO.setMenuItemId( entity.getMenuItemId() );
        menuItemResponseDTO.setName( entity.getName() );
        menuItemResponseDTO.setDescription( entity.getDescription() );
        menuItemResponseDTO.setPrice( entity.getPrice() );
        menuItemResponseDTO.setIsAvailable( entity.getIsAvailable() );

        return menuItemResponseDTO;
    }
}
