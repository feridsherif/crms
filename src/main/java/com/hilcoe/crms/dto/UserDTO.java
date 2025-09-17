package com.hilcoe.crms.dto;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
	private Long userId;
	@NotBlank
	private String username;
	@NotBlank
	private String email;
	@NotNull
	private Set<Long> roleIds;
	@NotBlank
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}