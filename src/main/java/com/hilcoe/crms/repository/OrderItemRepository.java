package com.hilcoe.crms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hilcoe.crms.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByOrder_OrderId(Long orderId);
}