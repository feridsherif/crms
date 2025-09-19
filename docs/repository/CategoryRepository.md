# CategoryRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.Category;
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports JPA repository and Category entity.
- **public interface CategoryRepository ...**
  - Standard repository for Category entities.
