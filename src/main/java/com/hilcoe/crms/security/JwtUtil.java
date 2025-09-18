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

@Component
public class JwtUtil {
	private static final long TOKEN_VALIDITY = 60 * 60 * 10000;

	@Value("${jwt.secret:defaultsecretkeydefaultsecretkey}")
	private String secret;

	private Claims extractAllClaims(String token) {
		try {
			return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).getPayload();
		} catch (Exception e) {
			// Log the error and return null or throw a custom exception
			// logger.error("Invalid JWT token", e);
			return null;
		}
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		if (claims == null) {
			return null;
		}
		return claimsResolver.apply(claims);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Long extractUserId(String token) {
		Claims claims = extractAllClaims(token);
		if (claims == null) {
			return null;
		}
		Object userIdObj = claims.get("userId");
		if (userIdObj instanceof Integer) {
			return ((Integer) userIdObj).longValue();
		} else if (userIdObj instanceof Long) {
			return (Long) userIdObj;
		} else if (userIdObj instanceof String) {
			try {
				return Long.valueOf((String) userIdObj);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public String generateToken(User user) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + TOKEN_VALIDITY);
		return Jwts.builder().subject(user.getUsername()).claim("userId", user.getUserId()).issuedAt(now)
				.expiration(expiryDate).signWith(getSignKey()).compact();
	}

	private SecretKey getSignKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	private boolean isTokenExpired(String token) {
		Date expiration = extractExpiration(token);
		if (expiration == null) {
			// Treat tokens with no expiration as expired
			return true;
		}
		return expiration.before(new Date());
	}

	public boolean validateToken(String token, User user) {
		final String username = extractUsername(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}
}