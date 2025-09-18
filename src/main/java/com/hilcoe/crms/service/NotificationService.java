package com.hilcoe.crms.service;

public interface NotificationService {
	void sendToRole(String role, String title, String message, String dataJson);

	void sendToUser(Long userId, String title, String message, String dataJson);
}
