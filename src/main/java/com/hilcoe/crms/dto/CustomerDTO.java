package com.hilcoe.crms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;

public class CustomerDTO {
	private LocalDateTime createdAt;

	private Long customerId;

	@Size(max = 100)
	private String loyaltyId;

	private String name;

	private String notes;

	private String phone;

	private LocalDateTime updatedAt;
	private Long userId;

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

	public Long getUserId() {
		return userId;
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

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}