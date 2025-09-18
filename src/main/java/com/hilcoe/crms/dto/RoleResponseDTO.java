package com.hilcoe.crms.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoleResponseDTO {
	@NotBlank
	private String description;
	@NotBlank
	private String name;
	private Set<Long> permissionIds;
	private Set<String> permissionNames;
	@NotNull
	private Long roleId;

	public RoleResponseDTO() {
	}

	public RoleResponseDTO(@NotNull Long roleId, @NotBlank String name, @NotBlank String description,
			Set<Long> permissionIds, Set<String> permissionNames) {
		this.roleId = roleId;
		this.name = name;
		this.description = description;
		this.permissionIds = permissionIds;
		this.permissionNames = permissionNames;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Set<Long> getPermissionIds() {
		return permissionIds;
	}

	public Set<String> getPermissionNames() {
		return permissionNames;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPermissionIds(Set<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}

	public void setPermissionNames(Set<String> permissionNames) {
		this.permissionNames = permissionNames;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}