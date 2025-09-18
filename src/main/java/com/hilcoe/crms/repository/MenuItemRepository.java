package com.hilcoe.crms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hilcoe.crms.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>, JpaSpecificationExecutor<MenuItem> {
	Page<MenuItem> findByCategory_CategoryId(Long categoryId, Pageable pageable);
}