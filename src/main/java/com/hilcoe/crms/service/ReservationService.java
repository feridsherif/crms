package com.hilcoe.crms.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.entity.Reservation;
import com.hilcoe.crms.repository.ReservationRepository;
import com.hilcoe.crms.repository.ReservationSpecification;

@Service
public class ReservationService {
	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private NotificationService notificationService;

	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private ReservationRepository reservationRepository;

	public void cancelReservation(Long id, Long userId) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException(
						"Reservation not found with id: " + id));
		if (reservation.getStatus() != Reservation.ReservationStatus.PENDING) {
			throw new com.hilcoe.crms.exception.ReservationCancellationNotAllowedException(
					"Reservation can only be canceled if status is PENDING. Current status: "
							+ reservation.getStatus());
		}
		Reservation before = new Reservation();
		before.setReservationId(reservation.getReservationId());
		before.setCustomerId(reservation.getCustomerId());
		before.setTableId(reservation.getTableId());
		before.setBranchId(reservation.getBranchId());
		before.setReservationTime(reservation.getReservationTime());
		before.setPartySize(reservation.getPartySize());
		before.setStatus(reservation.getStatus());
		reservation.setStatus(Reservation.ReservationStatus.CANCELLED);
		Reservation updated = reservationRepository.save(reservation);
		HashMap<String, Object> data = new HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Reservation", updated.getReservationId(), data);
		// Notify customer and staff
		try {
			String dataJson = objectMapper.writeValueAsString(updated);
			notificationService.sendToUser(updated.getCustomerId(), "Reservation Cancelled",
					"Your reservation has been cancelled.", dataJson);
			notificationService.sendToRole("STAFF", "Reservation Cancelled", "A reservation has been cancelled.",
					dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
	}

	public ReservationResponseDTO completeReservation(Long id, Long userId) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException(
						"Reservation not found with id: " + id));
		if (reservation.getStatus() != Reservation.ReservationStatus.CONFIRMED) {
			throw new com.hilcoe.crms.exception.ReservationStatusUpdateNotAllowedException(id,
					"COMPLETED: Only CONFIRMED reservations can be completed.");
		}
		Reservation before = new Reservation();
		before.setReservationId(reservation.getReservationId());
		before.setCustomerId(reservation.getCustomerId());
		before.setTableId(reservation.getTableId());
		before.setBranchId(reservation.getBranchId());
		before.setReservationTime(reservation.getReservationTime());
		before.setPartySize(reservation.getPartySize());
		before.setStatus(reservation.getStatus());
		reservation.setStatus(Reservation.ReservationStatus.COMPLETED);
		Reservation updated = reservationRepository.save(reservation);
		HashMap<String, Object> data = new HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Reservation", updated.getReservationId(), data);
		// Notify customer and staff
		try {
			String dataJson = objectMapper.writeValueAsString(updated);
			notificationService.sendToUser(updated.getCustomerId(), "Reservation Completed",
					"Your reservation is completed.", dataJson);
			notificationService.sendToRole("STAFF", "Reservation Completed", "A reservation has been completed.",
					dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
		return toDTO(updated);
	}

	public ReservationResponseDTO confirmReservation(Long id, Long userId) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException(
						"Reservation not found with id: " + id));
		if (reservation.getStatus() != Reservation.ReservationStatus.PENDING) {
			throw new com.hilcoe.crms.exception.ReservationStatusUpdateNotAllowedException(id,
					"CONFIRMED: Only PENDING reservations can be confirmed.");
		}
		Reservation before = new Reservation();
		before.setReservationId(reservation.getReservationId());
		before.setCustomerId(reservation.getCustomerId());
		before.setTableId(reservation.getTableId());
		before.setBranchId(reservation.getBranchId());
		before.setReservationTime(reservation.getReservationTime());
		before.setPartySize(reservation.getPartySize());
		before.setStatus(reservation.getStatus());
		reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
		Reservation updated = reservationRepository.save(reservation);
		HashMap<String, Object> data = new HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Reservation", updated.getReservationId(), data);
		// Notify customer and staff
		try {
			String dataJson = objectMapper.writeValueAsString(updated);
			notificationService.sendToUser(updated.getCustomerId(), "Reservation Confirmed",
					"Your reservation is confirmed.", dataJson);
			notificationService.sendToRole("STAFF", "Reservation Confirmed", "A reservation has been confirmed.",
					dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
		return toDTO(updated);
	}

	public ReservationResponseDTO createReservation(ReservationCreateDTO dto, Long userId) {
		Reservation reservation = new Reservation();
		reservation.setCustomerId(dto.getCustomerId());
		reservation.setTableId(dto.getTableId());
		reservation.setBranchId(dto.getBranchId());
		reservation.setReservationTime(dto.getReservationTime());
		reservation.setPartySize(dto.getPartySize());
		reservation.setStatus(Reservation.ReservationStatus.PENDING); // Set default status
		Reservation saved = reservationRepository.save(reservation);
		auditLogService.log(userId, "CREATE", "Reservation", saved.getReservationId(), saved);
		// Notify customer and staff
		try {
			String dataJson = objectMapper.writeValueAsString(saved);
			notificationService.sendToUser(saved.getCustomerId(), "Reservation Created",
					"Your reservation has been created.", dataJson);
			notificationService.sendToRole("STAFF", "New Reservation", "A new reservation has been made.", dataJson);
		} catch (Exception e) {
			/* log or ignore */ }
		return toDTO(saved);
	}

	public void deleteReservation(Long id, Long userId) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException(
						"Reservation not found with id: " + id));
		if (reservation.getStatus() != Reservation.ReservationStatus.PENDING) {
			throw new com.hilcoe.crms.exception.ReservationUpdateNotAllowedException(
					"Reservation can only be deleted if status is PENDING. Current status: " + reservation.getStatus());
		}
		auditLogService.log(userId, "DELETE", "Reservation", reservation.getReservationId(), reservation);
		reservationRepository.deleteById(id);
	}

	public ReservationFullResponseDTO getReservation(Long id) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException(
						"Reservation not found with id: " + id));
		return toFullResponseDTO(reservation);
	}

	public List<ReservationFullResponseDTO> getReservations() {
		return reservationRepository.findAll().stream().map(this::toFullResponseDTO).collect(Collectors.toList());
	}

	public PaginatedResponseDTO<ReservationFullResponseDTO> getReservationsPaginated(Pageable pageable) {
		Page<Reservation> page = reservationRepository.findAll(pageable);
		List<ReservationFullResponseDTO> content = page.getContent().stream().map(this::toFullResponseDTO)
				.collect(Collectors.toList());
		int pageNum = page.getNumber();
		int pageSize = page.getSize();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		boolean hasNext = page.hasNext();
		boolean hasPrevious = page.hasPrevious();
		String sort = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public PaginatedResponseDTO<ReservationResponseDTO> getReservationsPaginatedAdvanced(String status, Long branchId,
			Long tableId, Long customerId, LocalDateTime minReservationTime, LocalDateTime maxReservationTime,
			Integer minPartySize, Integer maxPartySize, int page, int size, String sortBy, String direction) {
		Sort sort = Sort.by(
				direction != null && direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
				(sortBy != null && !sortBy.isEmpty()) ? sortBy : "reservationId");
		Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, sort);
		var spec = ReservationSpecification.filter(status, branchId, tableId, customerId, minReservationTime,
				maxReservationTime, minPartySize, maxPartySize);
		Page<Reservation> result = reservationRepository.findAll(spec, pageable);
		List<ReservationResponseDTO> content = result.getContent().stream().map(this::toDTO)
				.collect(Collectors.toList());
		int pageNum = result.getNumber();
		int pageSize = result.getSize();
		long totalElements = result.getTotalElements();
		int totalPages = result.getTotalPages();
		boolean hasNext = result.hasNext();
		boolean hasPrevious = result.hasPrevious();
		String sortStr = sort.toString();
		Object filter = null; // Optionally, build a filter summary
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	private ReservationResponseDTO toDTO(Reservation reservation) {
		ReservationResponseDTO dto = new ReservationResponseDTO();
		dto.setReservationId(reservation.getReservationId());
		dto.setStatus(reservation.getStatus().name());
		return dto;
	}

	private ReservationFullResponseDTO toFullResponseDTO(Reservation reservation) {
		return new ReservationFullResponseDTO(reservation.getReservationId(), reservation.getCustomerId(),
				reservation.getTableId(), reservation.getBranchId(), reservation.getReservationTime(),
				reservation.getPartySize(), reservation.getStatus().name());
	}

	public ReservationResponseDTO updateReservation(Long id, ReservationUpdateDTO dto, Long userId) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException(
						"Reservation not found with id: " + id));
		if (reservation.getStatus() == Reservation.ReservationStatus.CANCELLED
				|| reservation.getStatus() == Reservation.ReservationStatus.COMPLETED) {
			throw new com.hilcoe.crms.exception.ReservationUpdateNotAllowedException(id);
		}
		Reservation before = new Reservation();
		before.setReservationId(reservation.getReservationId());
		before.setCustomerId(reservation.getCustomerId());
		before.setTableId(reservation.getTableId());
		before.setBranchId(reservation.getBranchId());
		before.setReservationTime(reservation.getReservationTime());
		before.setPartySize(reservation.getPartySize());
		before.setStatus(reservation.getStatus());
		reservation.setReservationTime(dto.getReservationTime());
		reservation.setPartySize(dto.getPartySize());
		Reservation updated = reservationRepository.save(reservation);
		HashMap<String, Object> data = new HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Reservation", updated.getReservationId(), data);
		// Notify on status change
		try {
			String dataJson = objectMapper.writeValueAsString(updated);
			if (updated.getStatus() == Reservation.ReservationStatus.CONFIRMED) {
				notificationService.sendToUser(updated.getCustomerId(), "Reservation Confirmed",
						"Your reservation is confirmed.", dataJson);
				notificationService.sendToRole("STAFF", "Reservation Confirmed", "A reservation has been confirmed.",
						dataJson);
			} else if (updated.getStatus() == Reservation.ReservationStatus.COMPLETED) {
				notificationService.sendToUser(updated.getCustomerId(), "Reservation Completed",
						"Your reservation is completed.", dataJson);
				notificationService.sendToRole("STAFF", "Reservation Completed", "A reservation has been completed.",
						dataJson);
			} else if (updated.getStatus() == Reservation.ReservationStatus.CANCELLED) {
				notificationService.sendToUser(updated.getCustomerId(), "Reservation Cancelled",
						"Your reservation has been cancelled.", dataJson);
				notificationService.sendToRole("STAFF", "Reservation Cancelled", "A reservation has been cancelled.",
						dataJson);
			}
		} catch (Exception e) {
			/* log or ignore */ }
		return toDTO(updated);
	}
}