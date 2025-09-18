package com.hilcoe.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.PermissionDTO;
import com.hilcoe.crms.dto.PermissionResponseDTO;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.exception.PermissionNotFoundException;
import com.hilcoe.crms.mapper.PermissionMapper;
import com.hilcoe.crms.repository.PermissionRepository;

@Service
public class PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private PermissionRepository permissionRepository;

	public PermissionDTO createPermission(PermissionDTO dto) {
		Permission permission = new Permission(null, dto.getName(), dto.getDescription());
		permission = permissionRepository.save(permission);
		return toDTO(permission);
	}

	public void deletePermission(Long id) {
		if (!permissionRepository.existsById(id)) {
			throw new PermissionNotFoundException(id);
		}
		permissionRepository.deleteById(id);
	}

	public List<PermissionDTO> getAllPermissions() {
		return permissionRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	public PermissionDTO getPermissionById(Long id) {
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() -> new PermissionNotFoundException(id));
		return toDTO(permission);
	}

	public Page<PermissionResponseDTO> getPermissions(Pageable pageable) {
		return permissionRepository.findAll(pageable).map(permissionMapper::toResponseDTO);
	}

	public PaginatedResponseDTO<PermissionDTO> getPermissionsPaginated(Pageable pageable) {
		Page<Permission> page = permissionRepository.findAll(pageable);
		List<PermissionDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
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
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public PaginatedResponseDTO<PermissionDTO> getPermissionsPaginatedAdvanced(String name, String description,
			int page, int size, String sortBy, String direction) {
		org.springframework.data.domain.Sort sort = direction.equalsIgnoreCase("desc")
				? org.springframework.data.domain.Sort.by(sortBy).descending()
				: org.springframework.data.domain.Sort.by(sortBy).ascending();
		org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size,
				sort);
		org.springframework.data.domain.Page<Permission> permissionPage = permissionRepository.findAll(pageable);
		java.util.List<Permission> filtered = permissionPage.getContent().stream().filter(
				p -> name == null || (p.getName() != null && p.getName().toLowerCase().contains(name.toLowerCase())))
				.filter(p -> description == null || (p.getDescription() != null
						&& p.getDescription().toLowerCase().contains(description.toLowerCase())))
				.collect(java.util.stream.Collectors.toList());
		java.util.List<PermissionDTO> content = filtered.stream().map(this::toDTO)
				.collect(java.util.stream.Collectors.toList());
		int pageNum = permissionPage.getNumber();
		int pageSize = permissionPage.getSize();
		long totalElements = permissionPage.getTotalElements();
		int totalPages = permissionPage.getTotalPages();
		boolean hasNext = permissionPage.hasNext();
		boolean hasPrevious = permissionPage.hasPrevious();
		String sortStr = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	private PermissionDTO toDTO(Permission permission) {
		return new PermissionDTO(permission.getPermissionId(), permission.getName(), permission.getDescription());
	}

	public PermissionDTO updatePermission(Long id, PermissionDTO dto) {
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() -> new PermissionNotFoundException(id));
		permission.setName(dto.getName());
		permission.setDescription(dto.getDescription());
		permission = permissionRepository.save(permission);
		return toDTO(permission);
	}
}