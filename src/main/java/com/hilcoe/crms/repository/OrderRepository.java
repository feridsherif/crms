package com.hilcoe.crms.repository;

import com.hilcoe.crms.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
