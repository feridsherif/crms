package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderResponseDTO {
	@NotNull
	private Long orderId;
	@NotBlank
	private String status;

	public OrderResponseDTO() {
		super();
	}

	public OrderResponseDTO(@NotNull Long orderId, @NotBlank String status) {
		super();
		this.orderId = orderId;
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}