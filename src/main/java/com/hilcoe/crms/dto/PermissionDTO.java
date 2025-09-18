package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;

public class PermissionDTO {
	private String description;
	@NotBlank
	private String name;
	private Long permissionId;

	public PermissionDTO() {
		super();
	}

	public PermissionDTO(Long permissionId, @NotBlank String name, String description) {
		super();
		this.permissionId = permissionId;
		this.name = name;
		this.description = description;
	}

	public PermissionDTO(@NotBlank String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
}