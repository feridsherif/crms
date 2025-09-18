package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
