package com.hilcoe.crms.repository;

import com.hilcoe.crms.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findTop5ByInventoryItem_InventoryItemIdOrderByMovementIdDesc(Long inventoryItemId);
}