package com.hilcoe.crms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission {
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "name", nullable = false)
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id")
	private Long permissionId;

	public Permission() {
	}

	public Permission(Long permissionId, String name, String description) {
		this.permissionId = permissionId;
		this.name = name;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
}