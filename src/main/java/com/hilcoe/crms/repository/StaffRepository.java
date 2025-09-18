package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
