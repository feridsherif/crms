package com.hilcoe.crms.repository;

import com.hilcoe.crms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByUser_UserId(Long userId);
}