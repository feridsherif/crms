package com.hilcoe.crms.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserResponseDTO {
	@NotBlank
	private String email;
	@NotNull
	private Set<Long> roleIds;
	@NotNull
	private Long userId;
	@NotBlank
	private String username;

	public UserResponseDTO() {
		super();
	}

	public UserResponseDTO(@NotNull Long userId, @NotBlank String username, @NotBlank String email,
			@NotNull Set<Long> roleIds) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.roleIds = roleIds;
	}

	public String getEmail() {
		return email;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}