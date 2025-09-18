package com.hilcoe.crms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hilcoe.crms.entity.Notification;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.repository.NotificationRepository;
import com.hilcoe.crms.repository.UserRepository;

@Service
public class InAppNotificationService implements NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private UserRepository userRepository;

	public void deleteAllNotifications(Long userId) {
		var notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId, Pageable.unpaged());
		notificationRepository.deleteAll(notifications.getContent());
	}

	// Delete notification
	public boolean deleteNotification(Long notificationId, Long userId) {
		Optional<Notification> opt = notificationRepository.findById(notificationId);
		if (opt.isPresent() && opt.get().getUserId() != null && opt.get().getUserId().equals(userId)) {
			notificationRepository.deleteById(notificationId);
			return true;
		}
		return false;
	}

	// Fetch notifications for a user (paginated)
	public Page<Notification> getNotificationsForUser(Long userId, Pageable pageable) {
		return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
	}

	// Get unread notification count for a user
	public long getUnreadCountForUser(Long userId) {
		return notificationRepository.countByUserIdAndIsReadFalse(userId);
	}

	public void markAllAsRead(Long userId) {
		var notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId, Pageable.unpaged());
		notifications.forEach(n -> {
			if (!n.isRead()) {
				n.setRead(true);
				notificationRepository.save(n);
			}
		});
	}

	// Mark notification as read
	public boolean markAsRead(Long notificationId, Long userId) {
		Optional<Notification> opt = notificationRepository.findById(notificationId);
		if (opt.isPresent() && opt.get().getUserId() != null && opt.get().getUserId().equals(userId)) {
			Notification n = opt.get();
			n.setRead(true);
			notificationRepository.save(n);
			return true;
		}
		return false;
	}

	@Override
	public void sendToRole(String role, String title, String message, String dataJson) {
		// Fan-out: create a notification for each user in the role
		var users = userRepository.findByRoleName(role);
		for (User user : users) {
			Notification notification = new Notification();
			notification.setUserId(user.getUserId());
			notification.setTitle(title);
			notification.setMessage(message);
			notification.setDataJson(dataJson);
			notification.setRead(false);
			// Set type/severity based on role or message context if needed
			notification.setType(Notification.NotificationType.INFO);
			notification.setSeverity(Notification.NotificationSeverity.LOW);
			notificationRepository.save(notification);
			System.out.printf("[Notification] To User %d (Role %s): %s - %s | %s\n", user.getUserId(), role, title,
					message, dataJson);
		}
	}

	@Override
	public void sendToUser(Long userId, String title, String message, String dataJson) {
		Notification notification = new Notification();
		notification.setUserId(userId);
		notification.setTitle(title);
		notification.setMessage(message);
		notification.setDataJson(dataJson);
		notification.setRead(false);
		notificationRepository.save(notification);
		// Optionally, also push via websocket/push here
		System.out.printf("[Notification] To User %d: %s - %s | %s\n", userId, title, message, dataJson);
	}
}