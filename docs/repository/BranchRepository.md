# BranchRepository.java

```java
package com.hilcoe.crms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hilcoe.crms.entity.Branch;
public interface BranchRepository extends JpaRepository<Branch, Long> {
}
```

## Explanation

- **package com.hilcoe.crms.repository;**
  - Declares the package for the repository.
- **import org.springframework.data.jpa.repository.JpaRepository;**
  - Imports the Spring Data JPA repository interface.
- **import com.hilcoe.crms.entity.Branch;**
  - Imports the Branch entity.
- **public interface BranchRepository extends JpaRepository<Branch, Long> { }**
  - Declares a repository interface for Branch entities.
  - Extends JpaRepository, providing CRUD operations for Branch with a primary key of type Long.
  - No custom methods are defined; all standard JPA methods are inherited.
