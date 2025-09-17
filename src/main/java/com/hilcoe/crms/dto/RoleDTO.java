package com.hilcoe.crms.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
	private Long roleId;
	@NotBlank
	private String name;
	private String description;
	private Set<Long> permissionIds;

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Long> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(Set<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}
}