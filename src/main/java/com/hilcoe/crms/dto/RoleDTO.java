package com.hilcoe.crms.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
	private String description;
	@NotBlank
	private String name;
	private Set<Long> permissionIds;
	private Long roleId;

	public RoleDTO() {
		super();
	}

	public RoleDTO(Long roleId, @NotBlank String name, String description, Set<Long> permissionIds) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.description = description;
		this.permissionIds = permissionIds;
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

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}