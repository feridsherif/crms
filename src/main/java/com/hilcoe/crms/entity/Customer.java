package com.hilcoe.crms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(name = "loyalty_id", length = 100)
	private String loyaltyId;

	@Column(name = "name", length = 100)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String notes;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true, nullable = true, foreignKey = @ForeignKey(name = "fk_customer_user"))
	private User user;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	// Getters and Setters
	public Long getCustomerId() {
		return customerId;
	}

	public String getLoyaltyId() {
		return loyaltyId;
	}

	public String getName() {
		return name;
	}

	public String getNotes() {
		return notes;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public User getUser() {
		return user;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setLoyaltyId(String loyaltyId) {
		this.loyaltyId = loyaltyId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setUser(User user) {
		this.user = user;
	}
}