package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SupplierResponseDTO {
	@NotBlank
	private String contact;
	@NotBlank
	private String name;
	@NotBlank
	private String phone;
	@NotNull
	private Long supplierId;
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

	public String getContact() {
		return contact;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public String getTerms() {
		return terms;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}
}