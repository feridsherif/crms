package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PermissionResponseDTO {
	@NotBlank
	private String description;
	@NotBlank
	private String name;
	@NotNull
	private Long permissionId;

	public PermissionResponseDTO() {
		super();
	}

	public PermissionResponseDTO(@NotNull Long permissionId, @NotBlank String name, @NotBlank String description) {
		super();
		this.permissionId = permissionId;
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