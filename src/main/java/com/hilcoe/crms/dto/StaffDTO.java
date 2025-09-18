package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StaffDTO {
	@NotBlank
	private String contact;
	@NotNull
	private Long roleId;
	@NotNull
	private Long userId;

	public StaffDTO() {
		super();
	}

	public StaffDTO(@NotNull Long userId, @NotNull Long roleId, @NotBlank String contact) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.contact = contact;
	}

	public String getContact() {
		return contact;
	}

	public Long getRoleId() {
		return roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}