package com.hilcoe.crms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MenuItemDTO {
	@NotNull
	private Long categoryId;
	@NotBlank
	private String description;
	@NotNull
	private Boolean isAvailable;
	@NotBlank
	private String name;
	@NotNull
	private BigDecimal price;

	public MenuItemDTO() {
		super();
	}

	public MenuItemDTO(Long categoryId, String name, String description, BigDecimal price, Boolean isAvailable) {
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
