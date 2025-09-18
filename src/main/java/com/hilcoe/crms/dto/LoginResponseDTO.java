package com.hilcoe.crms.dto;

import java.util.List;

public class LoginResponseDTO {
	private List<String> permissions;
	private Long roleId;
	private String token;
	private Long userId;
	private String username;

	public LoginResponseDTO() {
	}

	public LoginResponseDTO(String token, String username, Long userId, Long roleId, List<String> permissions) {
		this.token = token;
		this.username = username;
		this.userId = userId;
		this.roleId = roleId;
		this.permissions = permissions;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getToken() {
		return token;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}