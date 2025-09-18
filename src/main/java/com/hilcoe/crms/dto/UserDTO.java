package com.hilcoe.crms.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@NotNull
	private Set<Long> roleIds;
	private Long userId;
	@NotBlank
	private String username;

	public UserDTO() {
		super();
	}

	public UserDTO(Long userId, @NotBlank String username, @NotBlank String email, @NotNull Set<Long> roleIds,
			@NotBlank String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.roleIds = roleIds;
		this.password = password;
	}

	public UserDTO(@NotBlank String username, @NotBlank String email, @NotNull Set<Long> roleIds,
			@NotBlank String password) {
		super();
		this.username = username;
		this.email = email;
		this.roleIds = roleIds;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
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

	public void setPassword(String password) {
		this.password = password;
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