# MenuItemRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.hilcoe.crms.entity.MenuItem;
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>, JpaSpecificationExecutor<MenuItem> {
    Page<MenuItem> findByCategory_CategoryId(Long categoryId, Pageable pageable);
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import ...**
  - Imports for pagination, JPA, and MenuItem entity.
- **public interface MenuItemRepository ...**
  - Repository for MenuItem entities.
  - `findByCategory_CategoryId(Long categoryId, Pageable pageable)`: Paginated retrieval of menu items by category.
