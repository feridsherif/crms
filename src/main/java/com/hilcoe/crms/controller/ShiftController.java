package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.ShiftDTO;
import com.hilcoe.crms.dto.ShiftResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.ShiftService;
import com.hilcoe.crms.exception.ShiftNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @PostMapping
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> createShift(@Valid @RequestBody ShiftDTO dto) {
        ShiftResponseDTO created = shiftService.createShift(dto);
        return ResponseEntity.status(201).body(ApiResponse.success("Shift created successfully", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ShiftResponseDTO>>> getShifts() {
        List<ShiftResponseDTO> result = shiftService.getAllShifts();
        return ResponseEntity.ok(ApiResponse.success("Shifts retrieved successfully", result));
    }

    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<PaginatedResponseDTO<ShiftResponseDTO>>> getShiftsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "shiftId") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        PaginatedResponseDTO<ShiftResponseDTO> result = shiftService.getShiftsPaginated(page, size, sortBy, direction);
        return ResponseEntity.ok(ApiResponse.success("Shifts retrieved successfully", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> getShift(@PathVariable Long id) {
        ShiftResponseDTO shift = shiftService.getShift(id);
        return ResponseEntity.ok(ApiResponse.success("Shift retrieved successfully", shift));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> updateShift(@PathVariable Long id, @RequestBody ShiftDTO dto) {
        ShiftResponseDTO updated = shiftService.updateShift(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Shift updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
        return ResponseEntity.ok(ApiResponse.success("Shift deleted successfully", null));
    }

    @ExceptionHandler(ShiftNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound(ShiftNotFoundException ex) {
        return ResponseEntity.status(404).body(ApiResponse.error(ex.getMessage()));
    }
}