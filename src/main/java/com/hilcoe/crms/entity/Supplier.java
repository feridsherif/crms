package com.hilcoe.crms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private Long supplierId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "contact", nullable = false)
	private String contact;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "terms", nullable = false)
	private String terms;

	public Supplier() {
	}

	public Supplier(Long supplierId, String name, String contact, String phone, String terms) {
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