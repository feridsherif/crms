package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
