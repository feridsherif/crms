package com.hilcoe.crms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {
	public enum NotificationSeverity {
		CRITICAL, HIGH, LOW, MEDIUM
	}

	public enum NotificationType {
		ERROR, INFO, SUCCESS, SYSTEM, WARNING
	}

	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(columnDefinition = "TEXT")
	private String dataJson;

	@Column(name = "is_read")
	private boolean isRead = false;

	private String message;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationId;
	@Deprecated
	private String role; // deprecated, use fan-out
	@Enumerated(EnumType.STRING)
	private NotificationSeverity severity = NotificationSeverity.LOW;
	private String title;
	@Enumerated(EnumType.STRING)
	private NotificationType type = NotificationType.INFO;
	private Long userId; // nullable if sent to a role

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getDataJson() {
		return dataJson;
	}

	public String getMessage() {
		return message;
	}

	// Getters and setters
	public Long getNotificationId() {
		return notificationId;
	}

	/**
	 * @deprecated Role field is deprecated. Use userId fan-out for role
	 *             notifications.
	 */
	@Deprecated
	public String getRole() {
		return role;
	}

	public NotificationSeverity getSeverity() {
		return severity;
	}

	public String getTitle() {
		return title;
	}

	public NotificationType getType() {
		return type;
	}

	public Long getUserId() {
		return userId;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Deprecated
	public void setRole(String role) {
		this.role = role;
	}

	public void setSeverity(NotificationSeverity severity) {
		this.severity = severity;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}