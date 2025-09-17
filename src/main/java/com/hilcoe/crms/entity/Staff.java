package com.hilcoe.crms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private Long staffId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Column(name = "contact", nullable = false)
	private String contact;

	public Staff() {
	}

	public Staff(Long staffId, Long userId, Role role, String contact) {
		this.staffId = staffId;
		this.userId = userId;
		this.role = role;
		this.contact = contact;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Role getRole() {
		return role;
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

	public Long getRoleId() {
		return role != null ? role.getRoleId() : null;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}