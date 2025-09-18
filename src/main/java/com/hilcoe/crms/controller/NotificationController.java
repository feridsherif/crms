package com.hilcoe.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.entity.Notification;
import com.hilcoe.crms.service.InAppNotificationService;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
	@Autowired
	private InAppNotificationService notificationService;

	// Delete all notifications for current user
	@DeleteMapping("/delete-all")
	public ResponseEntity<ApiResponse<?>> deleteAllNotifications() {
		Long userId = getCurrentUserId();
		notificationService.deleteAllNotifications(userId);
		return ResponseEntity.ok(ApiResponse.success("All notifications deleted", null));
	}

	// Delete notification
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteNotification(@PathVariable Long id) {
		Long userId = getCurrentUserId();
		boolean success = notificationService.deleteNotification(id, userId);
		if (success) {
			return ResponseEntity.ok(ApiResponse.success("Notification deleted", null));
		} else {
			return ResponseEntity.status(404).body(ApiResponse.error("Notification not found or not owned by user"));
		}
	}

	// Helper to get current userId from security context
	private Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			throw new RuntimeException("No authenticated user found");
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof com.hilcoe.crms.entity.User) {
			return ((com.hilcoe.crms.entity.User) principal).getUserId();
		} else {
			throw new RuntimeException("Unexpected principal type: " + principal.getClass());
		}
	}

	// Get notifications for current user (paginated)
	@GetMapping
	public ResponseEntity<ApiResponse<PaginatedResponseDTO<Notification>>> getNotifications(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Long userId = getCurrentUserId();
		Pageable pageable = PageRequest.of(page, size);
		Page<Notification> notificationPage = notificationService.getNotificationsForUser(userId, pageable);
		PaginatedResponseDTO<Notification> paginated = new PaginatedResponseDTO<>(notificationPage.getContent(),
				notificationPage.getNumber(), notificationPage.getSize(), notificationPage.getTotalElements(),
				notificationPage.getTotalPages(), notificationPage.hasNext(), notificationPage.hasPrevious(),
				notificationPage.getSort().toString(), null,
				notificationPage.getNumber() * notificationPage.getSize() + 1,
				notificationPage.getNumber() * notificationPage.getSize() + notificationPage.getNumberOfElements(),
				null, null);
		return ResponseEntity.ok(ApiResponse.success(paginated));
	}

	// Get unread notification count for current user
	@GetMapping("/unread-count")
	public ResponseEntity<ApiResponse<Long>> getUnreadCount() {
		Long userId = getCurrentUserId();
		long count = notificationService.getUnreadCountForUser(userId);
		return ResponseEntity.ok(ApiResponse.success(count));
	}

	// Mark all notifications as read for current user
	@PatchMapping("/mark-all-read")
	public ResponseEntity<ApiResponse<?>> markAllAsRead() {
		Long userId = getCurrentUserId();
		notificationService.markAllAsRead(userId);
		return ResponseEntity.ok(ApiResponse.success("All notifications marked as read", null));
	}

	// Mark notification as read
	@PatchMapping("/{id}/read")
	public ResponseEntity<ApiResponse<?>> markAsRead(@PathVariable Long id) {
		Long userId = getCurrentUserId();
		boolean success = notificationService.markAsRead(id, userId);
		if (success) {
			return ResponseEntity.ok(ApiResponse.success("Notification marked as read", null));
		} else {
			return ResponseEntity.status(404).body(ApiResponse.error("Notification not found or not owned by user"));
		}
	}
}