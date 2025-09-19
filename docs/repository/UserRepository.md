# UserRepository.java

```java
package com.hilcoe.crms.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hilcoe.crms.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions WHERE u.username = :username")
    Optional<User> findByUsernameWithRolesAndPermissions(@Param("username") String username);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports for collections, JPA, custom queries, and the User entity.
- **public interface UserRepository ...**
  - Extends JpaRepository for CRUD operations.
  - `existsByUsername(String username)`: Checks if a user exists by username.
  - `findByRoleName(String roleName)`: Custom JPQL query to find users by role name.
  - `findByUsername(String username)`: Finds a user by username, returning an Optional.
  - `findByUsernameWithRolesAndPermissions(String username)`: Fetches a user by username, eagerly loading roles and permissions.
