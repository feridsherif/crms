# StaffRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.Staff;
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and Staff entity.
- **public interface StaffRepository ...**
  - Standard repository for Staff entities.
