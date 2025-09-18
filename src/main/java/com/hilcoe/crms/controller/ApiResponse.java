package com.hilcoe.crms.controller;

public class ApiResponse<T> {
	public static <T> ApiResponse<T> error(String message) {
		return new ApiResponse<>("error", message, null);
	}
	public static <T> ApiResponse<T> error(String message, T data) {
		return new ApiResponse<>("error", message, data);
	}
	public static <T> ApiResponse<T> failed(String message) {
		return new ApiResponse<>("failed", message, null);
	}

	public static <T> ApiResponse<T> failed(String message, T data) {
		return new ApiResponse<>("failed", message, data);
	}

	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<>("success", message, data);
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>("success", null, data);
	}

	private T data;

	private String message;

	private String status;

	public ApiResponse(String status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}