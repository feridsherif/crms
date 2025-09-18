package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryDTO {
	@NotBlank
	public String description;
	@NotBlank
	public String name;
}
