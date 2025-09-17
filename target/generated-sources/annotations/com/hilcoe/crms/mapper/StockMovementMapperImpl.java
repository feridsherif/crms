package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.StockMovementDTO;
import com.hilcoe.crms.entity.InventoryItem;
import com.hilcoe.crms.entity.StockMovement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T09:18:42+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class StockMovementMapperImpl implements StockMovementMapper {

    @Override
    public StockMovementDTO toDTO(StockMovement entity) {
        if ( entity == null ) {
            return null;
        }

        StockMovementDTO stockMovementDTO = new StockMovementDTO();

        stockMovementDTO.setInventoryItemId( entityInventoryItemInventoryItemId( entity ) );
        stockMovementDTO.setMovementId( entity.getMovementId() );
        stockMovementDTO.setQuantityChange( entity.getQuantityChange() );
        stockMovementDTO.setReason( entity.getReason() );
        stockMovementDTO.setCreatedBy( entity.getCreatedBy() );

        return stockMovementDTO;
    }

    @Override
    public StockMovement toEntity(StockMovementDTO dto) {
        if ( dto == null ) {
            return null;
        }

        StockMovement stockMovement = new StockMovement();

        stockMovement.setInventoryItem( stockMovementDTOToInventoryItem( dto ) );
        stockMovement.setMovementId( dto.getMovementId() );
        stockMovement.setQuantityChange( dto.getQuantityChange() );
        stockMovement.setReason( dto.getReason() );
        stockMovement.setCreatedBy( dto.getCreatedBy() );

        return stockMovement;
    }

    private Long entityInventoryItemInventoryItemId(StockMovement stockMovement) {
        if ( stockMovement == null ) {
            return null;
        }
        InventoryItem inventoryItem = stockMovement.getInventoryItem();
        if ( inventoryItem == null ) {
            return null;
        }
        Long inventoryItemId = inventoryItem.getInventoryItemId();
        if ( inventoryItemId == null ) {
            return null;
        }
        return inventoryItemId;
    }

    protected InventoryItem stockMovementDTOToInventoryItem(StockMovementDTO stockMovementDTO) {
        if ( stockMovementDTO == null ) {
            return null;
        }

        InventoryItem inventoryItem = new InventoryItem();

        inventoryItem.setInventoryItemId( stockMovementDTO.getInventoryItemId() );

        return inventoryItem;
    }
}
