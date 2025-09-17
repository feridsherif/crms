package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import com.hilcoe.crms.entity.Reservation;
import com.hilcoe.crms.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.AuditLogService;
import java.util.HashMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AuditLogService auditLogService;

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
        return toDTO(saved);
    }

    public ReservationResponseDTO updateReservation(Long id, ReservationUpdateDTO dto, Long userId) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() == Reservation.ReservationStatus.CANCELLED ||
            reservation.getStatus() == Reservation.ReservationStatus.COMPLETED) {
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
        return toDTO(updated);
    }

    public void cancelReservation(Long id, Long userId) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() != Reservation.ReservationStatus.PENDING) {
            throw new com.hilcoe.crms.exception.ReservationCancellationNotAllowedException(
                "Reservation can only be canceled if status is PENDING. Current status: " + reservation.getStatus());
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
    }

    public void deleteReservation(Long id, Long userId) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() != Reservation.ReservationStatus.PENDING) {
            throw new com.hilcoe.crms.exception.ReservationUpdateNotAllowedException(
                "Reservation can only be deleted if status is PENDING. Current status: " + reservation.getStatus());
        }
        auditLogService.log(userId, "DELETE", "Reservation", reservation.getReservationId(), reservation);
        reservationRepository.deleteById(id);
    }

    public ReservationResponseDTO confirmReservation(Long id, Long userId) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() != Reservation.ReservationStatus.PENDING) {
            throw new com.hilcoe.crms.exception.ReservationStatusUpdateNotAllowedException(id, "CONFIRMED: Only PENDING reservations can be confirmed.");
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
        return toDTO(updated);
    }

    public ReservationResponseDTO completeReservation(Long id, Long userId) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() != Reservation.ReservationStatus.CONFIRMED) {
            throw new com.hilcoe.crms.exception.ReservationStatusUpdateNotAllowedException(id, "COMPLETED: Only CONFIRMED reservations can be completed.");
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
        return toDTO(updated);
    }

    public ReservationFullResponseDTO getReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        return toFullResponseDTO(reservation);
    }

    public List<ReservationFullResponseDTO> getReservations() {
        return reservationRepository.findAll().stream().map(this::toFullResponseDTO).collect(Collectors.toList());
    }

    public PaginatedResponseDTO<ReservationFullResponseDTO> getReservationsPaginated(Pageable pageable) {
        Page<Reservation> page = reservationRepository.findAll(pageable);
        List<ReservationFullResponseDTO> content = page.getContent().stream().map(this::toFullResponseDTO).collect(Collectors.toList());
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
        return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

    private ReservationResponseDTO toDTO(Reservation reservation) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setReservationId(reservation.getReservationId());
        dto.setStatus(reservation.getStatus().name());
        return dto;
    }

    private ReservationFullResponseDTO toFullResponseDTO(Reservation reservation) {
        return new ReservationFullResponseDTO(
            reservation.getReservationId(),
            reservation.getCustomerId(),
            reservation.getTableId(),
            reservation.getBranchId(),
            reservation.getReservationTime(),
            reservation.getPartySize(),
            reservation.getStatus().name()
        );
    }
}