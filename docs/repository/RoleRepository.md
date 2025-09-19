# RoleRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.Role;
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and Role entity.
- **public interface RoleRepository ...**
  - Repository for Role entities.
  - `findByName(String name)`: Finds a role by its name.
