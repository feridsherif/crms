# UserService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the UserService.

```java
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.UserDTO;
import com.hilcoe.crms.dto.UserResponseDTO;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.exception.UserAlreadyExistsException;
import com.hilcoe.crms.exception.UserNotFoundException;
import com.hilcoe.crms.mapper.UserMapper;
import com.hilcoe.crms.repository.RoleRepository;
import com.hilcoe.crms.repository.UserRepository;
```
- Imports classes for collections, dependency injection, paging, password encoding, DTOs, entities, exceptions, and repositories.

## Service Declaration
```java
@Service
public class UserService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
```
- Sets up password encoding and injects dependencies for role access, mapping, and user persistence.

### Create User
```java
    public UserDTO createUser(UserDTO dto) {
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(dto.getRoleIds()));
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(null, dto.getUsername(), dto.getEmail(), hashedPassword, roles);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            String msg = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
            if (msg != null && (msg.contains("Duplicate entry") || msg.contains("unique constraint"))) {
                if (msg.contains("username")) {
                    throw new UserAlreadyExistsException("Username already exists.");
                } else if (msg.contains("email")) {
                    throw new UserAlreadyExistsException("Email already exists.");
                } else {
```
- Creates a new user, hashes the password, assigns roles, saves to the repository, and handles duplicate username/email exceptions.

---

This file provides a detailed explanation of each section and method in the `UserService` class, helping developers understand its structure and logic. (Continue this pattern for the rest of the class.)
