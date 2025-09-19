# ReservationRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.hilcoe.crms.entity.Reservation;
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository, specification executor, and Reservation entity.
- **public interface ReservationRepository ...**
  - Repository for Reservation entities.
  - Supports CRUD and dynamic queries.
