# NotificationController.java - Line-by-Line Explanation

This document provides a detailed, line-by-line explanation of the `NotificationController` class, which manages in-app notifications for users.

---

## Package and Imports

```java
package com.hilcoe.crms.controller;
```
- Declares the package for the controller.

```java
import ... // Spring, DTO, and service imports
```
- Imports Spring REST, DTOs, and the notification service.

---

## Class Declaration and Dependency Injection

```java
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    @Autowired
    private InAppNotificationService notificationService;
```
- `@RestController`: Marks this class as a REST controller.
- `@RequestMapping("/api/v1/notifications")`: Sets the base URL for all endpoints in this controller.
- `@Autowired`: Injects the notification service.

---

## Endpoint: Delete All Notifications

```java
@DeleteMapping("/delete-all")
public ResponseEntity<ApiResponse<?>> deleteAllNotifications() {
    Long userId = getCurrentUserId();
    notificationService.deleteAllNotifications(userId);
    return ResponseEntity.ok(ApiResponse.success("All notifications deleted", null));
}
```
- `@DeleteMapping("/delete-all")`: Handles DELETE requests to `/api/v1/notifications/delete-all`.
- Deletes all notifications for the current user.

---

## Endpoint: Delete Notification by ID

```java
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
```
- `@DeleteMapping("/{id}")`: Handles DELETE requests to `/api/v1/notifications/{id}`.
- Deletes a specific notification for the current user.

---

## Helper: Get Current User ID

```java
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
```
- Retrieves the current user's ID from the security context.

---

## Endpoint: Get Notifications (Paginated)

```java
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
```
- `@GetMapping`: Handles GET requests to `/api/v1/notifications`.
- Returns paginated notifications for the current user.

---

## Endpoint: Get Unread Notification Count

```java
@GetMapping("/unread-count")
public ResponseEntity<ApiResponse<Long>> getUnreadCount() {
    Long userId = getCurrentUserId();
    long count = notificationService.getUnreadCountForUser(userId);
    return ResponseEntity.ok(ApiResponse.success(count));
}
```
- `@GetMapping("/unread-count")`: Handles GET requests to `/api/v1/notifications/unread-count`.
- Returns the count of unread notifications for the current user.

---

## Endpoint: Mark All Notifications as Read

```java
@PatchMapping("/mark-all-read")
public ResponseEntity<ApiResponse<?>> markAllAsRead() {
    Long userId = getCurrentUserId();
    notificationService.markAllAsRead(userId);
    return ResponseEntity.ok(ApiResponse.success("All notifications marked as read", null));
}
```
- `@PatchMapping("/mark-all-read")`: Handles PATCH requests to `/api/v1/notifications/mark-all-read`.
- Marks all notifications as read for the current user.

---

## Endpoint: Mark Notification as Read

```java
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
```
- `@PatchMapping("/{id}/read")`: Handles PATCH requests to `/api/v1/notifications/{id}/read`.
- Marks a specific notification as read for the current user.

---

## Summary

- This controller manages notification CRUD and status update operations for the authenticated user.
- Uses helper methods to extract the user ID from the security context.
- Supports paginated retrieval, marking as read, and deletion of notifications.
