# OrderItemRepository.java

```java
package com.hilcoe.crms.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder_OrderId(Long orderId);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository, collections, and OrderItem entity.
- **public interface OrderItemRepository ...**
  - Repository for OrderItem entities.
  - `findByOrder_OrderId(Long orderId)`: Retrieves all order items for a given order ID.
