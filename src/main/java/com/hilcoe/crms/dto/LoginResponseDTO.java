package com.hilcoe.crms.dto;

import java.util.List;

public class LoginResponseDTO {
    private String token;
    private String username;
    private Long userId;
    private Long roleId;
    private List<String> permissions;

    public LoginResponseDTO() {}
    public LoginResponseDTO(String token, String username, Long userId, Long roleId, List<String> permissions) {
        this.token = token;
        this.username = username;
        this.userId = userId;
        this.roleId = roleId;
        this.permissions = permissions;
    }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    public List<String> getPermissions() { return permissions; }
    public void setPermissions(List<String> permissions) { this.permissions = permissions; }
}