# NotificationRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hilcoe.crms.entity.Notification;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    long countByUserIdAndIsReadFalse(Long userId);
    Page<Notification> findByRoleOrderByCreatedAtDesc(String role, Pageable pageable);
    Page<Notification> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports for pagination, JPA, and Notification entity.
- **@Repository**
  - Marks this interface as a Spring repository bean.
- **public interface NotificationRepository ...**
  - Repository for Notification entities.
  - `countByUserIdAndIsReadFalse(Long userId)`: Counts unread notifications for a user.
  - `findByRoleOrderByCreatedAtDesc(String role, Pageable pageable)`: Paged notifications by role, newest first.
  - `findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable)`: Paged notifications for a user, newest first.
