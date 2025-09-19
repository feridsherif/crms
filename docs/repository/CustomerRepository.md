# CustomerRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.hilcoe.crms.entity.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    boolean existsByUser_UserId(Long userId);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Package declaration.
- **import ...**
  - Imports JPA repository and specification executor for advanced queries, and the Customer entity.
- **public interface CustomerRepository ...**
  - Extends JpaRepository for CRUD and JpaSpecificationExecutor for dynamic queries.
  - `existsByUser_UserId(Long userId)`: Custom method to check if a Customer exists for a given userId (navigates the relationship to User).
