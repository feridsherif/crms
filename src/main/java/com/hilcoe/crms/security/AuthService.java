package com.hilcoe.crms.security;

import com.hilcoe.crms.dto.LoginRequestDTO;
import com.hilcoe.crms.dto.LoginResponseDTO;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.entity.Role;
import com.hilcoe.crms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;

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
			primaryRoleId = userRoles.stream()
				.min((r1, r2) -> Long.compare(r1.getRoleId(), r2.getRoleId()))
				.map(r -> r.getRoleId())
				.orElse(null);
		}
		List<String> permissions = userRoles == null ? List.of() : userRoles.stream()
			.flatMap(role -> role.getPermissions().stream())
			.map(permission -> permission.getName())
			.distinct()
			.collect(Collectors.toList());
		return new LoginResponseDTO(token, user.getUsername(), user.getUserId(), primaryRoleId, permissions);
	}

	public void logout(String token) {

	}
}