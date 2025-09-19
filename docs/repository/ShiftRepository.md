# ShiftRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.Shift;
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and the Shift entity.
- **public interface ShiftRepository ...**
  - Standard repository for Shift entities.
  - Inherits all CRUD operations from JpaRepository.
