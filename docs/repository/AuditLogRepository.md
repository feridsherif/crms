# AuditLogRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.AuditLog;
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and AuditLog entity.
- **public interface AuditLogRepository ...**
  - Standard repository for AuditLog entities.
