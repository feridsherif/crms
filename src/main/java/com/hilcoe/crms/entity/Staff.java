package com.hilcoe.crms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {
	@Column(name = "contact", nullable = false)
	private String contact;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private Long staffId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	public Staff() {
	}

	public Staff(Long staffId, Long userId, Role role, String contact) {
		this.staffId = staffId;
		this.userId = userId;
		this.role = role;
		this.contact = contact;
	}

	public String getContact() {
		return contact;
	}

	public Role getRole() {
		return role;
	}

	public Long getRoleId() {
		return role != null ? role.getRoleId() : null;
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

	public void setRole(Role role) {
		this.role = role;
	}

	public void setRoleId(Long roleId) {
		if (this.role == null) {
			this.role = new Role();
		}
		this.role.setRoleId(roleId);
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}