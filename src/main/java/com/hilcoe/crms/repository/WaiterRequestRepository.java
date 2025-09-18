package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.WaiterRequest;

public interface WaiterRequestRepository extends JpaRepository<WaiterRequest, Long> {
}
