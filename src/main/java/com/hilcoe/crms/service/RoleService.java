package com.hilcoe.crms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.RoleDTO;
import com.hilcoe.crms.dto.RoleResponseDTO;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.exception.RoleNotFoundException;
import com.hilcoe.crms.mapper.RoleMapper;
import com.hilcoe.crms.repository.PermissionRepository;
import com.hilcoe.crms.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleRepository roleRepository;

	public RoleDTO createRole(RoleDTO dto, Long userId) {
		Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(dto.getPermissionIds()));
		Role role = new Role(null, dto.getName(), dto.getDescription());
		role.setPermissions(permissions);
		role = roleRepository.save(role);
		auditLogService.log(userId, "CREATE", "Role", role.getRoleId(), role);
		return toDTO(role);
	}

	public void deleteRole(Long id, Long userId) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		auditLogService.log(userId, "DELETE", "Role", role.getRoleId(), role);
		roleRepository.deleteById(id);
	}

	public List<RoleResponseDTO> getAllRoles() {
		return roleRepository.findAll().stream().map(roleMapper::toResponseDTO).collect(Collectors.toList());
	}

	public RoleResponseDTO getRoleById(Long id) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		return roleMapper.toResponseDTO(role);
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
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public PaginatedResponseDTO<RoleDTO> getRolesPaginatedAdvanced(String name, String description, int page, int size,
			String sortBy, String direction) {
		org.springframework.data.domain.Sort sort = direction.equalsIgnoreCase("desc")
				? org.springframework.data.domain.Sort.by(sortBy).descending()
				: org.springframework.data.domain.Sort.by(sortBy).ascending();
		org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size,
				sort);
		org.springframework.data.domain.Page<Role> rolePage = roleRepository.findAll(pageable);
		java.util.List<Role> filtered = rolePage.getContent().stream().filter(
				r -> name == null || (r.getName() != null && r.getName().toLowerCase().contains(name.toLowerCase())))
				.filter(r -> description == null || (r.getDescription() != null
						&& r.getDescription().toLowerCase().contains(description.toLowerCase())))
				.collect(java.util.stream.Collectors.toList());
		java.util.List<RoleDTO> content = filtered.stream().map(this::toDTO)
				.collect(java.util.stream.Collectors.toList());
		int pageNum = rolePage.getNumber();
		int pageSize = rolePage.getSize();
		long totalElements = rolePage.getTotalElements();
		int totalPages = rolePage.getTotalPages();
		boolean hasNext = rolePage.hasNext();
		boolean hasPrevious = rolePage.hasPrevious();
		String sortStr = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	private RoleDTO toDTO(Role role) {
		Set<Long> permissionIds = role.getPermissions().stream().map(Permission::getPermissionId)
				.collect(Collectors.toSet());
		return new RoleDTO(role.getRoleId(), role.getName(), role.getDescription(), permissionIds);
	}

	public RoleDTO updateRole(Long id, RoleDTO dto, Long userId) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
		Role before = new Role(role.getRoleId(), role.getName(), role.getDescription());
		before.setPermissions(new HashSet<>(role.getPermissions()));
		role.setName(dto.getName());
		role.setDescription(dto.getDescription());
		Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(dto.getPermissionIds()));
		role.setPermissions(permissions);
		Role updated = roleRepository.save(role);
		java.util.HashMap<String, Object> data = new java.util.HashMap<>();
		data.put("before", before);
		data.put("after", updated);
		auditLogService.log(userId, "UPDATE", "Role", updated.getRoleId(), data);
		return toDTO(updated);
	}
}