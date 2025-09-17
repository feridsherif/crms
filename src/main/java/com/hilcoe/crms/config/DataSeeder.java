package com.hilcoe.crms.config;

import com.hilcoe.crms.entity.Permission;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.repository.PermissionRepository;
import com.hilcoe.crms.repository.RoleRepository;
import com.hilcoe.crms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;
import java.util.*;

@Configuration
public class DataSeeder {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seed() {
        // 1. Seed Permissions
        String[] entities = {"BRANCH", "INVENTORY", "MENU", "ORDER", "RESERVATION", "STAFF", "USER", "ROLE", "PERMISSION", "WAITER_REQUEST", "CATEGORY", "SUPPLIER", "SHIFT", "AUDIT_LOG"};
        String[] actions = {"READ", "CREATE", "UPDATE", "DELETE"};
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

        // 2. Seed Admin Role
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

        // 3. Seed Admin User
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@gmail.com");
            adminUser.setPasswordHash(passwordEncoder.encode("12345678"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            adminUser.setRoles(roles);
            userRepository.save(adminUser);
        }
    }
}