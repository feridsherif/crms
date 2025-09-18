package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BranchResponseDTO {
	@NotBlank
	private String address;
	@NotNull
	private Long branchId;
	@NotBlank
	private String name;
	@NotBlank
	private String phone;

	public BranchResponseDTO() {
		super();
	}

	public BranchResponseDTO(@NotNull Long branchId, @NotBlank String name, @NotBlank String address,
			@NotBlank String phone) {
		super();
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