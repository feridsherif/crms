package com.hilcoe.crms.repository;

import com.hilcoe.crms.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Page<MenuItem> findByCategory_CategoryId(Long categoryId, Pageable pageable);
}