package com.hilcoe.crms.controller;

import com.hilcoe.crms.dto.BranchDTO;
import com.hilcoe.crms.dto.BranchResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping
    public ResponseEntity<Object> addBranch(@Valid @RequestBody BranchDTO dto) {
        BranchResponseDTO response = branchService.addBranch(dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Branch added", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBranch(@PathVariable Long id, @Valid @RequestBody BranchDTO dto) {
        BranchResponseDTO response = branchService.updateBranch(id, dto);
        return ResponseEntity.ok(new ApiResponse<>("success", "Branch updated", response));
    }

    @GetMapping
    public ResponseEntity<Object> getBranches() {
        List<BranchResponseDTO> branches = branchService.getBranches();
        return ResponseEntity.ok(new ApiResponse<>("success", "Branches fetched", branches));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getBranchesPaginated(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        PaginatedResponseDTO<BranchResponseDTO> branches = branchService.getBranchesPaginated(PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiResponse<>("success", "Branches fetched", branches));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        ApiResponse<Void> response = new ApiResponse<>("success", "Branch deleted", null);
        return ResponseEntity.ok(response);
    }
}