package com.hilcoe.crms.config;

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

@Component
public class AuditLogFilter extends OncePerRequestFilter {
	private static final Pattern ENTITY_PATTERN = Pattern.compile("/api/v1/([^/]+)(?:/([0-9]+))?");
	@Autowired
	private AuditLogRepository auditLogRepository;

	@Autowired
	private JwtUtil jwtUtil;

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

	private String extractEntity(String uri) {
		Matcher matcher = ENTITY_PATTERN.matcher(uri);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	private Long extractEntityId(String uri) {
		Matcher matcher = ENTITY_PATTERN.matcher(uri);
		if (matcher.find() && matcher.group(2) != null) {
			try {
				return Long.parseLong(matcher.group(2));
			} catch (NumberFormatException ignored) {
			}
		}
		return null;
	}

	private Long extractUserId(String token) {
		return jwtUtil.extractUserId(token);
	}
}