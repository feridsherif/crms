# PermissionRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.Permission;
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByName(String name);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and Permission entity.
- **public interface PermissionRepository ...**
  - Repository for Permission entities.
  - `existsByName(String name)`: Checks if a permission exists by name.
