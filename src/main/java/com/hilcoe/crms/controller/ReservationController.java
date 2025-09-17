package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import com.hilcoe.crms.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.hilcoe.crms.dto.PaginatedResponseDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Object> createReservation(@Valid @RequestBody ReservationCreateDTO dto) {
        ReservationResponseDTO response = reservationService.createReservation(dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservation created", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationUpdateDTO dto) {
        ReservationResponseDTO response = reservationService.updateReservation(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservation updated", response));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Object> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservation cancelled", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservation deleted", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReservation(@PathVariable Long id) {
        ReservationFullResponseDTO reservation = reservationService.getReservation(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservation fetched", reservation));
    }

    @GetMapping
    public ResponseEntity<Object> getReservations() {
        List<ReservationFullResponseDTO> reservations = reservationService.getReservations();
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservations fetched", reservations));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getReservationsPaginated(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PaginatedResponseDTO<ReservationFullResponseDTO> reservations = reservationService.getReservationsPaginated(pageable);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservations fetched", reservations));
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<Object> confirmReservation(@PathVariable Long id) {
        ReservationResponseDTO response = reservationService.confirmReservation(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservation confirmed", response));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Object> completeReservation(@PathVariable Long id) {
        ReservationResponseDTO response = reservationService.completeReservation(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Reservation completed", response));
    }
}