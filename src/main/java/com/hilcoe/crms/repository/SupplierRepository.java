package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
