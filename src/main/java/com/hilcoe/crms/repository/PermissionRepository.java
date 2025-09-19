package com.hilcoe.crms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	boolean existsByName(String name);

	Optional<Permission> findByName(String name);
}