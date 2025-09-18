package com.hilcoe.crms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hilcoe.crms.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	long countByUserIdAndIsReadFalse(Long userId);

	Page<Notification> findByRoleOrderByCreatedAtDesc(String role, Pageable pageable);

	Page<Notification> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}