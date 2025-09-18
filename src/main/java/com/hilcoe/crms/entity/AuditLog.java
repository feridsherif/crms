package com.hilcoe.crms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
	@Column(name = "action", nullable = false)
	private String action;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id")
	private Long auditId;

	@Column(name = "data", columnDefinition = "TEXT")
	private String data;

	@Column(name = "entity", nullable = false)
	private String entity;

	@Column(name = "entity_id", nullable = false)
	private Long entityId;

	@Column(name = "timestamp", nullable = false)
	private LocalDateTime timestamp;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	public AuditLog() {
	}

	public AuditLog(Long auditId, Long userId, String action, String entity, Long entityId,
			java.time.LocalDateTime timestamp, String data) {
		this.auditId = auditId;
		this.userId = userId;
		this.action = action;
		this.entity = entity;
		this.entityId = entityId;
		this.timestamp = timestamp;
		this.data = data;
	}

	public String getAction() {
		return action;
	}

	public Long getAuditId() {
		return auditId;
	}

	public String getData() {
		return data;
	}

	public String getEntity() {
		return entity;
	}

	public Long getEntityId() {
		return entityId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Long getUserId() {
		return userId;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}