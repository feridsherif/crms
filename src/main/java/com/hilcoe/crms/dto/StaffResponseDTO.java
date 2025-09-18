package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StaffResponseDTO {
	@NotBlank
	private String contact;
	@NotNull
	private Long roleId;
	@NotNull
	private Long staffId;
	@NotNull
	private Long userId;

	public StaffResponseDTO() {
		super();
	}

	public StaffResponseDTO(@NotNull Long staffId, @NotNull Long userId, @NotNull Long roleId,
			@NotBlank String contact) {
		super();
		this.staffId = staffId;
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

	public Long getStaffId() {
		return staffId;
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

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}