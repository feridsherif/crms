# CustomAuthenticationEntryPoint.java

**Purpose:**
Handles authentication failures by returning a JSON error response.

---

## Code Explanation

```java
package com.hilcoe.crms.security;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilcoe.crms.controller.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
```
- Imports for Spring Security, JSON serialization, and HTTP handling.

```java
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
```
- Registers as a Spring component and implements the entry point for authentication errors.

```java
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

        ApiResponse<Object> apiResponse = new ApiResponse<>("error",
                "Authentication failed: token expired or user unauthenticated", null);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(apiResponse));
    }
}
```
- When authentication fails:
  - Sets response type to JSON and status to 401.
  - Returns a standardized error message as JSON.
