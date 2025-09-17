package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public class RoleResponseDTO {
    @NotNull
    private Long roleId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Set<Long> permissionIds;
    private Set<String> permissionNames;

    public RoleResponseDTO() {}

    public RoleResponseDTO(@NotNull Long roleId, @NotBlank String name, @NotBlank String description, Set<Long> permissionIds, Set<String> permissionNames) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.permissionIds = permissionIds;
        this.permissionNames = permissionNames;
    }

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<Long> getPermissionIds() { return permissionIds; }
    public void setPermissionIds(Set<Long> permissionIds) { this.permissionIds = permissionIds; }
    public Set<String> getPermissionNames() { return permissionNames; }
    public void setPermissionNames(Set<String> permissionNames) { this.permissionNames = permissionNames; }
}