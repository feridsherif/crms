package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.BranchDTO;
import com.hilcoe.crms.dto.BranchResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.Branch;
import com.hilcoe.crms.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {
	@Autowired
	private BranchRepository branchRepository;

	public BranchResponseDTO addBranch(BranchDTO dto) {
		Branch branch = new Branch();
		branch.setName(dto.getName());
		branch.setAddress(dto.getAddress());
		branch.setPhone(dto.getPhone());
		Branch saved = branchRepository.save(branch);
		return toResponseDTO(saved);
	}

	public BranchResponseDTO updateBranch(Long id, BranchDTO dto) {
		Branch branch = branchRepository.findById(id)
			.orElseThrow(() -> new com.hilcoe.crms.exception.BranchNotFoundException("Branch not found with id: " + id));
		branch.setName(dto.getName());
		branch.setAddress(dto.getAddress());
		branch.setPhone(dto.getPhone());
		Branch updated = branchRepository.save(branch);
		return toResponseDTO(updated);
	}

	public List<BranchResponseDTO> getBranches() {
		return branchRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
	}

	public void deleteBranch(Long id) {
		branchRepository.findById(id)
			.orElseThrow(() -> new com.hilcoe.crms.exception.BranchNotFoundException("Branch not found with id: " + id));
		branchRepository.deleteById(id);
	}

	public PaginatedResponseDTO<BranchResponseDTO> getBranchesPaginated(Pageable pageable) {
        Page<Branch> page = branchRepository.findAll(pageable);
        List<BranchResponseDTO> content = page.getContent().stream().map(this::toResponseDTO).collect(Collectors.toList());
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

	private BranchResponseDTO toResponseDTO(Branch branch) {
		BranchResponseDTO dto = new BranchResponseDTO();
		dto.setBranchId(branch.getBranchId());
		dto.setName(branch.getName());
		dto.setAddress(branch.getAddress());
		dto.setPhone(branch.getPhone());
		return dto;
	}
}