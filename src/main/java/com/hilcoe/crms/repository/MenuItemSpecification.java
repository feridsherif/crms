package com.hilcoe.crms.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.hilcoe.crms.entity.MenuItem;

import jakarta.persistence.criteria.Predicate;

public class MenuItemSpecification {
	public static Specification<MenuItem> filter(String name, Long categoryId, Boolean isAvailable, BigDecimal minPrice,
			BigDecimal maxPrice) {
		return (root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			if (name != null && !name.isEmpty()) {
				predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
			}
			if (categoryId != null) {
				predicate = cb.and(predicate, cb.equal(root.get("category").get("categoryId"), categoryId));
			}
			if (isAvailable != null) {
				predicate = cb.and(predicate, cb.equal(root.get("isAvailable"), isAvailable));
			}
			if (minPrice != null) {
				predicate = cb.and(predicate, cb.ge(root.get("price"), minPrice));
			}
			if (maxPrice != null) {
				predicate = cb.and(predicate, cb.le(root.get("price"), maxPrice));
			}
			return predicate;
		};
	}
}
