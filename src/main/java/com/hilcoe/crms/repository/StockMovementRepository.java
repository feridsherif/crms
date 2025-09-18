package com.hilcoe.crms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
	List<StockMovement> findTop5ByInventoryItem_InventoryItemIdOrderByMovementIdDesc(Long inventoryItemId);
}