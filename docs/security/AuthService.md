# AuthService.java

**Purpose:**
Handles authentication logic, including login and (placeholder) logout.

---

## Code Explanation

```java
package com.hilcoe.crms.security;
```
- Declares the package.

```java
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.LoginRequestDTO;
import com.hilcoe.crms.dto.LoginResponseDTO;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.repository.UserRepository;
```
- Imports required classes for dependency injection, security, DTOs, entities, and repository access.

```java
@Service
public class AuthService {
```
- Marks the class as a Spring service.

```java
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
```
- Injects dependencies for JWT utility, password encoding, and user data access.

### login Method

```java
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        String token = jwtUtil.generateToken(user);
        Long primaryRoleId = null;
        Set<Role> userRoles = user.getRoles();
        if (userRoles != null && !userRoles.isEmpty()) {
            primaryRoleId = userRoles.stream().min((r1, r2) -> Long.compare(r1.getRoleId(), r2.getRoleId()))
                    .map(r -> r.getRoleId()).orElse(null);
        }
        List<String> permissions = userRoles == null ? List.of()
                : userRoles.stream().flatMap(role -> role.getPermissions().stream())
                        .map(permission -> permission.getName()).distinct().collect(Collectors.toList());
        return new LoginResponseDTO(token, user.getUsername(), user.getUserId(), primaryRoleId, permissions);
    }
```
- Authenticates a user:
  - Looks up the user by username.
  - Checks the password.
  - Generates a JWT token.
  - Determines the user's primary role (lowest roleId).
  - Collects all unique permissions from the user's roles.
  - Returns a DTO with token, username, userId, primaryRoleId, and permissions.

### logout Method

```java
    public void logout(String token) {
        // No implementation (stateless JWT)
    }
}
```
- Placeholder for logout (not needed for stateless JWT).
