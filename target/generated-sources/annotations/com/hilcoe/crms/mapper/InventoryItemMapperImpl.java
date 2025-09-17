package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import com.hilcoe.crms.entity.InventoryItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-16T19:12:55+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class InventoryItemMapperImpl implements InventoryItemMapper {

    @Override
    public InventoryItemDTO toDTO(InventoryItem entity) {
        if ( entity == null ) {
            return null;
        }

        InventoryItemDTO inventoryItemDTO = new InventoryItemDTO();

        inventoryItemDTO.setName( entity.getName() );
        inventoryItemDTO.setUnit( entity.getUnit() );
        inventoryItemDTO.setQuantity( entity.getQuantity() );
        inventoryItemDTO.setThreshold( entity.getThreshold() );

        return inventoryItemDTO;
    }

    @Override
    public InventoryItem toEntity(InventoryItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        InventoryItem inventoryItem = new InventoryItem();

        inventoryItem.setName( dto.getName() );
        inventoryItem.setUnit( dto.getUnit() );
        inventoryItem.setQuantity( dto.getQuantity() );
        inventoryItem.setThreshold( dto.getThreshold() );

        return inventoryItem;
    }

    @Override
    public InventoryItemResponseDTO toResponseDTO(InventoryItem entity) {
        if ( entity == null ) {
            return null;
        }

        InventoryItemResponseDTO inventoryItemResponseDTO = new InventoryItemResponseDTO();

        inventoryItemResponseDTO.setInventoryItemId( entity.getInventoryItemId() );
        inventoryItemResponseDTO.setName( entity.getName() );
        inventoryItemResponseDTO.setUnit( entity.getUnit() );
        inventoryItemResponseDTO.setQuantity( entity.getQuantity() );
        inventoryItemResponseDTO.setThreshold( entity.getThreshold() );

        return inventoryItemResponseDTO;
    }
}
