package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;

public class StatusDTO {
	@NotBlank
	private String status;

	public StatusDTO() {
	}

	public StatusDTO(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
