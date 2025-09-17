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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationResponseDTO createReservation(ReservationCreateDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(dto.getCustomerId());
        reservation.setTableId(dto.getTableId());
        reservation.setBranchId(dto.getBranchId());
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setPartySize(dto.getPartySize());
        reservation.setStatus(Reservation.ReservationStatus.PENDING); // Set default status
        Reservation saved = reservationRepository.save(reservation);
        return toDTO(saved);
    }

    public ReservationResponseDTO updateReservation(Long id, ReservationUpdateDTO dto) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() == Reservation.ReservationStatus.CANCELLED ||
            reservation.getStatus() == Reservation.ReservationStatus.COMPLETED) {
            throw new com.hilcoe.crms.exception.ReservationUpdateNotAllowedException(id);
        }
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setPartySize(dto.getPartySize());
        Reservation updated = reservationRepository.save(reservation);
        return toDTO(updated);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() == Reservation.ReservationStatus.COMPLETED) {
            throw new com.hilcoe.crms.exception.ReservationCancellationNotAllowedException(id);
        }
        reservation.setStatus(Reservation.ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
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
        Object filter = null; // No filter logic for now
        long firstElementIndex = pageNum * pageSize + 1;
        long lastElementIndex = firstElementIndex + content.size() - 1;
        String nextPageUrl = null; // TODO: Generate if needed
        String previousPageUrl = null; // TODO: Generate if needed
        return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages,
            hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

    public ReservationResponseDTO confirmReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() == Reservation.ReservationStatus.CANCELLED ||
            reservation.getStatus() == Reservation.ReservationStatus.COMPLETED) {
            throw new com.hilcoe.crms.exception.ReservationStatusUpdateNotAllowedException(id, "CONFIRMED");
        }
        reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        Reservation updated = reservationRepository.save(reservation);
        return toDTO(updated);
    }

    public ReservationResponseDTO completeReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException("Reservation not found with id: " + id));
        if (reservation.getStatus() == Reservation.ReservationStatus.CANCELLED ||
            reservation.getStatus() == Reservation.ReservationStatus.COMPLETED) {
            throw new com.hilcoe.crms.exception.ReservationStatusUpdateNotAllowedException(id, "COMPLETED");
        }
        reservation.setStatus(Reservation.ReservationStatus.COMPLETED);
        Reservation updated = reservationRepository.save(reservation);
        return toDTO(updated);
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