package com.hilcoe.crms.dto;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class UserResponseDTO {
	@NotNull
	private Long userId;
	@NotBlank
	private String username;
	@NotBlank
	private String email;
	@NotNull
	private Set<Long> roleIds;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}
}