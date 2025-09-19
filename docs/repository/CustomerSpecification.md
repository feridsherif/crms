# CustomerSpecification.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.domain.Specification;
import com.hilcoe.crms.entity.Customer;
import jakarta.persistence.criteria.Predicate;

public class CustomerSpecification {
    public static Specification<Customer> filter(String name, String email, String phone) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (name != null && !name.isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (email != null && !email.isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }
            if (phone != null && !phone.isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("phone")), "%" + phone.toLowerCase() + "%"));
            }
            return predicate;
        };
    }
}
```

## Explanation
- **filter(String name, String email, String phone)**: Returns a JPA Specification for filtering customers by name, email, and phone. Each filter is applied only if the parameter is not null or empty, using case-insensitive partial matching.