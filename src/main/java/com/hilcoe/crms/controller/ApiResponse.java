package com.hilcoe.crms.controller;

public class ApiResponse<T> {
	private String status;
	private String message;
	private T data;

	public ApiResponse(String status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", null, data);
    }
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("success", message, data);
    }
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("error", message, null);
    }
}