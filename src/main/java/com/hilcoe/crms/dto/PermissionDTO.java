package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class PermissionDTO {
	private Long permissionId;
	@NotBlank
	private String name;
	private String description;

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

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
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
}