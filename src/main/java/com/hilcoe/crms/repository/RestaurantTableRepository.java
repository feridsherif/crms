package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.RestaurantTable;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
	boolean existsByTableNumberAndBranch_BranchId(String tableNumber, Long branchId);
}