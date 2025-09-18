package com.hilcoe.crms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier {
	@Column(name = "contact", nullable = false)
	private String contact;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private Long supplierId;

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