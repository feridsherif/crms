package com.hilcoe.crms.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.hilcoe.crms.entity.Reservation;

import jakarta.persistence.criteria.Predicate;

public class ReservationSpecification {
	public static Specification<Reservation> filter(String status, Long branchId, Long tableId, Long customerId,
			LocalDateTime minReservationTime, LocalDateTime maxReservationTime, Integer minPartySize,
			Integer maxPartySize) {
		return (root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			if (status != null && !status.isEmpty()) {
				predicate = cb.and(predicate, cb.equal(root.get("status"), status));
			}
			if (branchId != null) {
				predicate = cb.and(predicate, cb.equal(root.get("branchId"), branchId));
			}
			if (tableId != null) {
				predicate = cb.and(predicate, cb.equal(root.get("tableId"), tableId));
			}
			if (customerId != null) {
				predicate = cb.and(predicate, cb.equal(root.get("customerId"), customerId));
			}
			if (minReservationTime != null) {
				predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("reservationTime"), minReservationTime));
			}
			if (maxReservationTime != null) {
				predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("reservationTime"), maxReservationTime));
			}
			if (minPartySize != null) {
				predicate = cb.and(predicate, cb.ge(root.get("partySize"), minPartySize));
			}
			if (maxPartySize != null) {
				predicate = cb.and(predicate, cb.le(root.get("partySize"), maxPartySize));
			}
			return predicate;
		};
	}
}
