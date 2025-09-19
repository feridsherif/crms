# JwtAuthenticationFilter.java

**Purpose:**
Spring Security filter that authenticates requests using JWT tokens.

---

## Code Explanation

```java
package com.hilcoe.crms.security;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
```
- Imports for Spring Security, JWT, and HTTP filter handling.

```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
```
- Registers as a Spring component and injects dependencies.

### doFilterInternal Method

```java
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception e) {
                // Invalid token
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> userOpt = userRepository.findByUsernameWithRolesAndPermissions(username);
            if (userOpt.isPresent() && jwtUtil.validateToken(token, userOpt.get())) {
                User user = userOpt.get();
                // Collect permissions from all roles
                var authorities = user.getRoles() == null ? java.util.Collections.<GrantedAuthority>emptyList()
                        : user.getRoles().stream().flatMap(role -> role.getPermissions().stream())
                                .map(permission -> new SimpleGrantedAuthority(permission.getName())).distinct()
                                .collect(Collectors.toList());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
                        authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
```
- Extracts JWT from the Authorization header.
- Validates the token and loads the user.
- Sets the authentication in the security context with the user's permissions.
- Continues the filter chain.
