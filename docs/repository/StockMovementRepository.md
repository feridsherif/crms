# StockMovementRepository.java

```java
package com.hilcoe.crms.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.StockMovement;
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findTop5ByInventoryItem_InventoryItemIdOrderByMovementIdDesc(Long inventoryItemId);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository, collections, and StockMovement entity.
- **public interface StockMovementRepository ...**
  - Repository for StockMovement entities.
  - `findTop5ByInventoryItem_InventoryItemIdOrderByMovementIdDesc(Long inventoryItemId)`: Retrieves the 5 most recent stock movements for a given inventory item.
