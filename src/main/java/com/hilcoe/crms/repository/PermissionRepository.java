package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	boolean existsByName(String name);
}