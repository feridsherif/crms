# OrderSpecification.java

```java
package com.hilcoe.crms.repository;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import com.hilcoe.crms.entity.Order;
import jakarta.persistence.criteria.Predicate;

public class OrderSpecification {
    public static Specification<Order> filter(String status, Long branchId, Long tableId, Long staffId,
            BigDecimal minTotalAmount, BigDecimal maxTotalAmount
            // Add LocalDateTime dateFrom, dateTo if Order has a date field
    ) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (status != null && !status.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            }
            if (branchId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("branchId"), branchId));
            }
            if (tableId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("tableId"), tableId));
            }
            if (staffId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("staffId"), staffId));
            }
            if (minTotalAmount != null) {
                predicate = cb.and(predicate, cb.ge(root.get("totalAmount"), minTotalAmount));
            }
            if (maxTotalAmount != null) {
                predicate = cb.and(predicate, cb.le(root.get("totalAmount"), maxTotalAmount));
            }
            // If Order has a date field, add dateFrom/dateTo filtering here
            return predicate;
        };
    }
}
```

## Explanation
- **filter(...)**: Returns a JPA Specification for filtering orders by status, branch, table, staff, and total amount range. Each filter is applied only if the parameter is not null or empty.