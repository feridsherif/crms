package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.SupplierDTO;
import com.hilcoe.crms.dto.SupplierResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<Object> addSupplier(@Valid @RequestBody SupplierDTO dto) {
        SupplierResponseDTO response = supplierService.addSupplier(dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Supplier added", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierDTO dto) {
        SupplierResponseDTO response = supplierService.updateSupplier(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Supplier updated", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Supplier deleted", null));
    }

    @GetMapping
    public ResponseEntity<Object> getSuppliers() {
        List<SupplierResponseDTO> suppliers = supplierService.getSuppliers();
        return ResponseEntity.ok(new ApiResponse<>("success", "Suppliers fetched", suppliers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSupplier(@PathVariable Long id) {
        SupplierResponseDTO supplier = supplierService.getSupplier(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Supplier fetched", supplier));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getSuppliersPaginated(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PaginatedResponseDTO<SupplierResponseDTO> suppliers = supplierService.getSuppliersPaginated(pageable);
        return ResponseEntity.ok(new ApiResponse<>("success", "Suppliers fetched", suppliers));
    }
}
