package com.hilcoe.crms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.security.JwtUtil;
import com.hilcoe.crms.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ReservationService reservationService;

	@PatchMapping("/{id}/cancel")
	@PreAuthorize("hasAuthority('RESERVATION_UPDATE')")
	public ResponseEntity<Object> cancelReservation(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		reservationService.cancelReservation(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Reservation cancelled", null));
	}

	@PatchMapping("/{id}/complete")
	public ResponseEntity<Object> completeReservation(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		ReservationResponseDTO response = reservationService.completeReservation(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Reservation completed", response));
	}

	@PatchMapping("/{id}/confirm")
	public ResponseEntity<Object> confirmReservation(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		ReservationResponseDTO response = reservationService.confirmReservation(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Reservation confirmed", response));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('RESERVATION_CREATE')")
	public ResponseEntity<Object> createReservation(@Valid @RequestBody ReservationCreateDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		ReservationResponseDTO response = reservationService.createReservation(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Reservation created", response));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('RESERVATION_DELETE')")
	public ResponseEntity<Object> deleteReservation(@PathVariable Long id,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		reservationService.deleteReservation(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Reservation deleted", null));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getReservation(@PathVariable Long id) {
		ReservationFullResponseDTO reservation = reservationService.getReservation(id);
		return ResponseEntity.ok(ApiResponse.success("Reservation fetched", reservation));
	}

	@GetMapping
	public ResponseEntity<Object> getReservations() {
		List<ReservationFullResponseDTO> reservations = reservationService.getReservations();
		return ResponseEntity.ok(ApiResponse.success("Reservations fetched", reservations));
	}

	@GetMapping("/paginated")
	public ResponseEntity<Object> getReservationsPaginated(@RequestParam(required = false) String status,
			@RequestParam(required = false) Long branchId, @RequestParam(required = false) Long tableId,
			@RequestParam(required = false) Long customerId, @RequestParam(required = false) String minReservationTime,
			@RequestParam(required = false) String maxReservationTime,
			@RequestParam(required = false) Integer minPartySize, @RequestParam(required = false) Integer maxPartySize,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "reservationId") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		LocalDateTime minTime = minReservationTime != null ? LocalDateTime.parse(minReservationTime) : null;
		LocalDateTime maxTime = maxReservationTime != null ? LocalDateTime.parse(maxReservationTime) : null;
		PaginatedResponseDTO<ReservationResponseDTO> reservations = reservationService.getReservationsPaginatedAdvanced(
				status, branchId, tableId, customerId, minTime, maxTime, minPartySize, maxPartySize, page, size, sortBy,
				direction);
		return ResponseEntity.ok(ApiResponse.success("Reservations fetched", reservations));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('RESERVATION_UPDATE')")
	public ResponseEntity<Object> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationUpdateDTO dto,
			@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.replace("Bearer ", "");
		Long userId = jwtUtil.extractUserId(token);
		ReservationResponseDTO response = reservationService.updateReservation(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Reservation updated", response));
	}
}