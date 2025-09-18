package com.hilcoe.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.ShiftDTO;
import com.hilcoe.crms.dto.ShiftResponseDTO;
import com.hilcoe.crms.exception.ShiftNotFoundException;
import com.hilcoe.crms.service.ShiftService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftController {
	@Autowired
	private ShiftService shiftService;

	@PostMapping
	@PreAuthorize("hasAuthority('SHIFT_CREATE')")
	public ResponseEntity<ApiResponse<ShiftResponseDTO>> createShift(@Valid @RequestBody ShiftDTO dto,
			Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		ShiftResponseDTO created = shiftService.createShift(dto, userId);
		return ResponseEntity.status(201).body(ApiResponse.success("Shift created successfully", created));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteShift(@PathVariable Long id, Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		shiftService.deleteShift(id, userId);
		return ResponseEntity.ok(ApiResponse.success("Shift deleted successfully", null));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('SHIFT_READ')")
	public ResponseEntity<ApiResponse<ShiftResponseDTO>> getShift(@PathVariable Long id) {
		ShiftResponseDTO shift = shiftService.getShift(id);
		return ResponseEntity.ok(ApiResponse.success("Shift retrieved successfully", shift));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('SHIFT_READ')")
	public ResponseEntity<ApiResponse<List<ShiftResponseDTO>>> getShifts() {
		List<ShiftResponseDTO> result = shiftService.getAllShifts();
		return ResponseEntity.ok(ApiResponse.success("Shifts retrieved successfully", result));
	}

	@GetMapping("/paginated")
	@PreAuthorize("hasAuthority('SHIFT_READ')")
	public ResponseEntity<ApiResponse<PaginatedResponseDTO<ShiftResponseDTO>>> getShiftsPaginated(
			@RequestParam(required = false) Long staffId, @RequestParam(required = false) Long branchId,
			@RequestParam(required = false) String minStartTime, @RequestParam(required = false) String maxEndTime,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "shiftId") String sortBy,
			@RequestParam(defaultValue = "ASC") String direction) {
		PaginatedResponseDTO<ShiftResponseDTO> result = shiftService.getShiftsPaginatedAdvanced(staffId, branchId,
				minStartTime, maxEndTime, page, size, sortBy, direction);
		return ResponseEntity.ok(ApiResponse.success("Shifts retrieved successfully", result));
	}

	@ExceptionHandler(ShiftNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleNotFound(ShiftNotFoundException ex) {
		return ResponseEntity.status(404).body(ApiResponse.error(ex.getMessage()));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('SHIFT_UPDATE')")
	public ResponseEntity<ApiResponse<ShiftResponseDTO>> updateShift(@PathVariable Long id, @RequestBody ShiftDTO dto,
			Authentication authentication) {
		Long userId = Long.parseLong(authentication.getName());
		ShiftResponseDTO updated = shiftService.updateShift(id, dto, userId);
		return ResponseEntity.ok(ApiResponse.success("Shift updated successfully", updated));
	}
}