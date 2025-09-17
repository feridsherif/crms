package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;

public class SupplierDTO {
	@NotBlank
	private String name;
	@NotBlank
	private String contact;
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
