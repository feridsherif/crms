# JwtUtil.java

**Purpose:**
Utility for generating, parsing, and validating JWT tokens.

---

## Code Explanation

```java
package com.hilcoe.crms.security;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.hilcoe.crms.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
```
- Imports for JWT, cryptography, and Spring.

```java
@Component
public class JwtUtil {
    private static final long TOKEN_VALIDITY = 60 * 60 * 10000;
```
- Registers as a Spring component.
- Sets token validity duration.

```java
    @Value("${jwt.secret:defaultsecretkeydefaultsecretkey}")
    private String secret;
```
- Injects the JWT secret from application properties.

### extractAllClaims

```java
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            // Log the error and return null or throw a custom exception
            // logger.error("Invalid JWT token", e);
            return null;
        }
    }
```
- Parses the JWT and extracts claims, handling errors gracefully.

### extractClaim, extractExpiration, extractUserId, extractUsername

- Utility methods to extract specific claims (expiration, userId, username) from the token.

### generateToken

```java
    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TOKEN_VALIDITY);
        return Jwts.builder().subject(user.getUsername()).claim("userId", user.getUserId()).issuedAt(now)
                .expiration(expiryDate).signWith(getSignKey()).compact();
    }
```
- Generates a JWT for a user, including username and userId.

### getSignKey

```java
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
```
- Returns the signing key for JWT.

### isTokenExpired, validateToken

- Checks if a token is expired.
- Validates a token against a user.
