package com.hilcoe.crms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "branches")
public class Branch {
	@Column(name = "address", nullable = false)
	private String address;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id")
	private Long branchId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone", nullable = false)
	private String phone;

	public Branch() {
	}

	public Branch(Long branchId, String name, String address, String phone) {
		this.branchId = branchId;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public Long getBranchId() {
		return branchId;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}