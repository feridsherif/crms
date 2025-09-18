package com.hilcoe.crms.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToMany(mappedBy = "category")
	private List<MenuItem> menuItems;

	@Column(name = "name", nullable = false)
	private String name;

	public Category() {
	}

	public Category(Long categoryId, String name, String description, java.util.List<MenuItem> menuItems) {
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.menuItems = menuItems;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getDescription() {
		return description;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public String getName() {
		return name;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public void setName(String name) {
		this.name = name;
	}
}