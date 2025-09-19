# NotificationService.java - Line-by-Line Explanation

## Package and Interface Declaration
```java
package com.hilcoe.crms.service;

public interface NotificationService {
```
- Declares the package and the NotificationService interface.

## Methods
```java
    void sendToRole(String role, String title, String message, String dataJson);
```
- Sends a notification to all users with a specific role.
- Parameters:
  - `role`: The role to notify.
  - `title`: The notification title.
  - `message`: The notification message.
  - `dataJson`: Additional data in JSON format.

```java
    void sendToUser(Long userId, String title, String message, String dataJson);
```
- Sends a notification to a specific user.
- Parameters:
  - `userId`: The user to notify.
  - `title`: The notification title.
  - `message`: The notification message.
  - `dataJson`: Additional data in JSON format.

```
}
```
- End of the interface.

---

This file provides a detailed explanation of each method in the `NotificationService` interface, helping developers understand its contract and usage.