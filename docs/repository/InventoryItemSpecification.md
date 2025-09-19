# InventoryItemSpecification.java

```java
package com.hilcoe.crms.repository;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import com.hilcoe.crms.entity.InventoryItem;
import jakarta.persistence.criteria.Predicate;

public class InventoryItemSpecification {
    public static Specification<InventoryItem> filter(String name, Long supplierId, String unit, BigDecimal minQuantity,
            BigDecimal maxQuantity, BigDecimal minThreshold, BigDecimal maxThreshold) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (name != null && !name.isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (supplierId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("supplier").get("supplierId"), supplierId));
            }
            if (unit != null && !unit.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("unit")), unit.toLowerCase()));
            }
            if (minQuantity != null) {
                predicate = cb.and(predicate, cb.ge(root.get("quantity"), minQuantity));
            }
            if (maxQuantity != null) {
                predicate = cb.and(predicate, cb.le(root.get("quantity"), maxQuantity));
            }
            if (minThreshold != null) {
                predicate = cb.and(predicate, cb.ge(root.get("threshold"), minThreshold));
            }
            if (maxThreshold != null) {
                predicate = cb.and(predicate, cb.le(root.get("threshold"), maxThreshold));
            }
            return predicate;
        };
    }
}
```

## Explanation
- **filter(...)**: Returns a JPA Specification for filtering inventory items by name, supplier, unit, quantity, and threshold ranges. Each filter is applied only if the parameter is not null or empty.