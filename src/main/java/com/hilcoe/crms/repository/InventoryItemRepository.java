package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hilcoe.crms.entity.InventoryItem;

public interface InventoryItemRepository
		extends JpaRepository<InventoryItem, Long>, JpaSpecificationExecutor<InventoryItem> {
}