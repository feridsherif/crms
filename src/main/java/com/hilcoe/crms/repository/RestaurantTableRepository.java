package com.hilcoe.crms.repository;

import com.hilcoe.crms.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    boolean existsByTableNumberAndBranch_BranchId(String tableNumber, Long branchId);
}