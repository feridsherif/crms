# DataSeeder.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.config;
```
- Declares the package for the configuration class.

```java
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.repository.PermissionRepository;
import com.hilcoe.crms.repository.RoleRepository;
import com.hilcoe.crms.repository.UserRepository;
import jakarta.annotation.PostConstruct;
```
- Imports classes for collections, dependency injection, configuration, password encoding, entities, repositories, and lifecycle hooks.

## Class Declaration
```java
@Configuration
public class DataSeeder {
```
- Marks the class as a Spring configuration class.

### Dependencies
```java
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
```
- Injects beans for password encoding and repository access.

### Data Seeding Logic
```java
    @PostConstruct
    public void seed() {
```
- The `seed` method is annotated with `@PostConstruct`, so it runs after the bean is initialized.

#### 1. Seed Permissions
```java
        String[] entities = { "BRANCH", "INVENTORY", "MENU", "ORDER", "RESERVATION", "STAFF", "USER", "ROLE",
                "PERMISSION", "WAITER_REQUEST", "CATEGORY", "SUPPLIER", "SHIFT", "AUDIT_LOG", "TABLE", "CUSTOMER" };
        String[] actions = { "READ", "CREATE", "UPDATE", "DELETE" };
        for (String entity : entities) {
            for (String action : actions) {
                String name = entity + "_" + action;
                String description = entity.charAt(0) + entity.substring(1).toLowerCase() + " " + action.toLowerCase();
                if (!permissionRepository.existsByName(name)) {
                    Permission permission = new Permission();
                    permission.setName(name);
                    permission.setDescription(description);
                    permissionRepository.save(permission);
                }
            }
        }
```
- Iterates over all entity-action combinations, creates and saves permissions if they don't exist.

#### 2. Seed Admin Role
```java
        Role adminRole = roleRepository.findByName("Admin");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("Admin");
            adminRole.setDescription("administrative privileges");
            adminRole = roleRepository.save(adminRole);
        }
        // Assign all permissions to Admin role
        Set<Permission> allPermissions = new HashSet<>(permissionRepository.findAll());
        adminRole.setPermissions(allPermissions);
        roleRepository.save(adminRole);
```
- Ensures an Admin role exists and assigns all permissions to it.

#### 3. Seed Admin User
```java
        Optional<User> adminUserOpt = userRepository.findByUsername("admin");
        User adminUser;
        if (adminUserOpt.isPresent()) {
            adminUser = adminUserOpt.get();
        } else {
            adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@gmail.com");
            adminUser.setPasswordHash(passwordEncoder.encode("12345678"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            adminUser.setRoles(roles);
            adminUser = userRepository.save(adminUser);
        }
```
- Ensures an admin user exists with the Admin role and a default password.

```java
    }
}
```
- End of the class and method.

---

This file provides a detailed explanation of each section and method in the `DataSeeder` class, helping developers understand its structure and logic.
