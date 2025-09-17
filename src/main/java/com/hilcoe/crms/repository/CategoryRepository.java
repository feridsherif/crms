package com.hilcoe.crms.repository;

import com.hilcoe.crms.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
