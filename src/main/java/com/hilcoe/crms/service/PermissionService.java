package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.PermissionDTO;
import com.hilcoe.crms.dto.PermissionResponseDTO;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.exception.PermissionNotFoundException;
import com.hilcoe.crms.mapper.PermissionMapper;
import com.hilcoe.crms.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    public PermissionDTO createPermission(PermissionDTO dto) {
        Permission permission = new Permission(null, dto.getName(), dto.getDescription());
        permission = permissionRepository.save(permission);
        return toDTO(permission);
    }

    public PermissionDTO getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(() -> new PermissionNotFoundException(id));
        return toDTO(permission);
    }

    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PermissionDTO updatePermission(Long id, PermissionDTO dto) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(() -> new PermissionNotFoundException(id));
        permission.setName(dto.getName());
        permission.setDescription(dto.getDescription());
        permission = permissionRepository.save(permission);
        return toDTO(permission);
    }

    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new PermissionNotFoundException(id);
        }
        permissionRepository.deleteById(id);
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
        return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages,
            hasNext, hasPrevious, sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
    }

    private PermissionDTO toDTO(Permission permission) {
        return new PermissionDTO(permission.getPermissionId(), permission.getName(), permission.getDescription());
    }
}