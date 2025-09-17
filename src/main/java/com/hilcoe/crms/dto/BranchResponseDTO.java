package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class BranchResponseDTO {
    @NotNull
    private Long branchId;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}