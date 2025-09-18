package com.hilcoe.crms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.StockMovementDTO;
import com.hilcoe.crms.entity.InventoryItem;
import com.hilcoe.crms.entity.StockMovement;

@Mapper(componentModel = "spring", uses = { InventoryItemMapper.class })
public interface StockMovementMapper {
	StockMovementMapper INSTANCE = Mappers.getMapper(StockMovementMapper.class);

	default InventoryItem mapIdToInventoryItem(Long inventoryItemId) {
		if (inventoryItemId == null) {
			return null;
		}
		InventoryItem item = new InventoryItem();
		item.setInventoryItemId(inventoryItemId);
		return item;
	}

	default Long mapInventoryItemToId(InventoryItem inventoryItem) {
		return inventoryItem != null ? inventoryItem.getInventoryItemId() : null;
	}

	@Mapping(source = "inventoryItem.inventoryItemId", target = "inventoryItemId")
	@Mapping(source = "movementId", target = "movementId")
	StockMovementDTO toDTO(StockMovement entity);

	@Mapping(source = "inventoryItemId", target = "inventoryItem.inventoryItemId")
	@Mapping(source = "movementId", target = "movementId")
	StockMovement toEntity(StockMovementDTO dto);
}