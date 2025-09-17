package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryDTO {
    @NotBlank
    public String name;
    @NotBlank
    public String description;
}
