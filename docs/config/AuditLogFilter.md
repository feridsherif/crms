# AuditLogFilter.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.config;
```
- Declares the package for the configuration class.

```java
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.hilcoe.crms.entity.AuditLog;
import com.hilcoe.crms.repository.AuditLogRepository;
import com.hilcoe.crms.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
```
- Imports classes for date/time, regex, dependency injection, Spring filter, entity, repository, JWT utility, and servlet API.

## Class Declaration
```java
@Component
public class AuditLogFilter extends OncePerRequestFilter {
```
- Marks the class as a Spring component and extends `OncePerRequestFilter` to ensure a single execution per request.

### Static Pattern
```java
    private static final Pattern ENTITY_PATTERN = Pattern.compile("/api/v1/([^/]+)(?:/([0-9]+))?");
```
- Compiles a regex pattern to extract the entity and optional ID from API URIs.

### Dependencies
```java
    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private JwtUtil jwtUtil;
```
- Injects the audit log repository and JWT utility.

### Filter Logic
```java
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String method = request.getMethod();
        if (!"GET".equalsIgnoreCase(method)) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try {
                    Long userId = extractUserId(token);
                    if (userId != null) {
                        String uri = request.getRequestURI();
                        String action = method;
                        String entity = extractEntity(uri);
                        Long entityId = extractEntityId(uri);
                        AuditLog log = new AuditLog();
                        log.setUserId(userId);
                        log.setAction(action);
                        log.setEntity(entity != null ? entity : "unknown");
                        log.setEntityId(entityId != null ? entityId : 0L);
                        log.setTimestamp(LocalDateTime.now());
                        auditLogRepository.save(log);
                    }
                } catch (Exception ignored) {
                    // Do not block request on audit log failure
                }
            }
        }
        filterChain.doFilter(request, response);
    }
```
- For every non-GET request with a valid Bearer token, extracts user ID, entity, and entity ID, then logs the action. Any exception is ignored to avoid blocking the request.

### Helper Methods
```java
    private String extractEntity(String uri) {
        Matcher matcher = ENTITY_PATTERN.matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
```
- Extracts the entity name from the URI using regex.

```java
    private Long extractEntityId(String uri) {
        Matcher matcher = ENTITY_PATTERN.matcher(uri);
        if (matcher.find() && matcher.group(2) != null) {
            try {
                return Long.parseLong(matcher.group(2));
            } catch (NumberFormatException ignored) {}
        }
        return null;
    }
```
- Extracts the entity ID from the URI if present.

```java
    private Long extractUserId(String token) {
        return jwtUtil.extractUserId(token);
    }
```
- Uses the JWT utility to extract the user ID from the token.

---

This file provides a detailed explanation of each section and method in the `AuditLogFilter` class, helping developers understand its structure and logic.
