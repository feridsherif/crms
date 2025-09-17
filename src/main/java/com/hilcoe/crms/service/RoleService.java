package com.hilcoe.crms.service;

import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.dto.RoleResponseDTO;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.repository.RoleRepository;
import com.hilcoe.crms.repository.PermissionRepository;
import com.hilcoe.crms.exception.RoleNotFoundException;
import com.hilcoe.crms.mapper.RoleMapper;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleMapper roleMapper;

    public RoleDTO createRole(RoleDTO dto) {
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(dto.getPermissionIds()));
        Role role = new Role(null, dto.getName(), dto.getDescription());
        role.setPermissions(permissions);
        role = roleRepository.save(role);
        return toDTO(role);
    }

    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RoleNotFoundException(id));
        return roleMapper.toResponseDTO(role);
    }

    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toResponseDTO).collect(Collectors.toList());
    }

    public RoleDTO updateRole(Long id, RoleDTO dto) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RoleNotFoundException(id));
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(dto.getPermissionIds()));
        role.setPermissions(permissions);
        role = roleRepository.save(role);
        return toDTO(role);
    }

    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException(id);
        }
        roleRepository.deleteById(id);
    }

    public Page<RoleResponseDTO> getRoles(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::toResponseDTO);
    }

    public PaginatedResponseDTO<RoleDTO> getRolesPaginated(Pageable pageable) {
        Page<Role> page = roleRepository.findAll(pageable);
        List<RoleDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
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

    private RoleDTO toDTO(Role role) {
        Set<Long> permissionIds = role.getPermissions().stream().map(Permission::getPermissionId).collect(Collectors.toSet());
        return new RoleDTO(role.getRoleId(), role.getName(), role.getDescription(), permissionIds);
    }
}