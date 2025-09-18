package com.hilcoe.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
