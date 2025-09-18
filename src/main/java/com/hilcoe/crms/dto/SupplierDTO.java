package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;

public class SupplierDTO {
	@NotBlank
	private String contact;
	@NotBlank
	private String name;
	@NotBlank
	private String phone;
	@NotBlank
	private String terms;

	public SupplierDTO() {
		super();
	}

	public SupplierDTO(String name, String contact, String phone, String terms) {
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

	public void setTerms(String terms) {
		this.terms = terms;
	}
}
