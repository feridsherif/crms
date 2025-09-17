package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class StaffDTO {
	@NotNull
	private Long userId;
	@NotNull
	private Long roleId;
	@NotBlank
	private String contact;

	public StaffDTO() {
		super();
	}

	public StaffDTO(@NotNull Long userId, @NotNull Long roleId, @NotBlank String contact) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.contact = contact;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}