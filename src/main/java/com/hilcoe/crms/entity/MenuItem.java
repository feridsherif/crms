package com.hilcoe.crms.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_items")
public class MenuItem {
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "is_available", nullable = false)
	private Boolean isAvailable;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_item_id")
	private Long menuItemId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	public MenuItem() {
	}

	public MenuItem(Long menuItemId, Category category, String name, String description, java.math.BigDecimal price,
			Boolean isAvailable) {
		this.menuItemId = menuItemId;
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	public Category getCategory() {
		return category;
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

	public void setCategory(Category category) {
		this.category = category;
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