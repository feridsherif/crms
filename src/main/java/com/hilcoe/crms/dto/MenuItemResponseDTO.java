package com.hilcoe.crms.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MenuItemResponseDTO {
	@NotNull
	private Long categoryId;
	@NotBlank
	private String description;
	@NotNull
	private Boolean isAvailable;
	@NotNull
	private Long menuItemId;
	@NotBlank
	private String name;
	@NotNull
	private BigDecimal price;

	public MenuItemResponseDTO() {
		super();
	}

	public MenuItemResponseDTO(@NotNull Long menuItemId, @NotNull Long categoryId, @NotBlank String name,
			@NotBlank String description, @NotNull BigDecimal price, @NotNull Boolean isAvailable) {
		super();
		this.menuItemId = menuItemId;
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

	public Long getMenuItemId() {
		return menuItemId;
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

	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}