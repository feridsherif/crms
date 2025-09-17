package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class PermissionResponseDTO {
	@NotNull
	private Long permissionId;
	@NotBlank
	private String name;
	@NotBlank
	private String description;

	public PermissionResponseDTO() {
		super();
	}

	public PermissionResponseDTO(@NotNull Long permissionId, @NotBlank String name, @NotBlank String description) {
		super();
		this.permissionId = permissionId;
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