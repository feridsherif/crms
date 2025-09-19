# SupplierRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.Supplier;
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and Supplier entity.
- **public interface SupplierRepository ...**
  - Standard repository for Supplier entities.
