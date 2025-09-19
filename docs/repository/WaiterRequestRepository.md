# WaiterRequestRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.WaiterRequest;
public interface WaiterRequestRepository extends JpaRepository<WaiterRequest, Long> {
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and WaiterRequest entity.
- **public interface WaiterRequestRepository ...**
  - Standard repository for WaiterRequest entities.
