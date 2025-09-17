package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class SupplierResponseDTO {
	@NotNull
	private Long supplierId;
	@NotBlank
	private String name;
	@NotBlank
	private String contact;
	@NotBlank
	private String phone;
	@NotBlank
	private String terms;

	public SupplierResponseDTO() {
		super();
	}

	public SupplierResponseDTO(@NotNull Long supplierId, @NotBlank String name, @NotBlank String contact,
			@NotBlank String phone, @NotBlank String terms) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.contact = contact;
		this.phone = phone;
		this.terms = terms;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}
}