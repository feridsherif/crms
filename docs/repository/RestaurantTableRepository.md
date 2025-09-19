# RestaurantTableRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.RestaurantTable;
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    boolean existsByTableNumberAndBranch_BranchId(String tableNumber, Long branchId);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and RestaurantTable entity.
- **public interface RestaurantTableRepository ...**
  - Repository for RestaurantTable entities.
  - `existsByTableNumberAndBranch_BranchId(String tableNumber, Long branchId)`: Checks if a table number exists in a branch.
