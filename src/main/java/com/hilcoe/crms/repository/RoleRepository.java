package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}