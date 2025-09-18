package com.hilcoe.crms.service;

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

@Service
public class UserService {
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;

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
					throw new UserAlreadyExistsException("User with provided details already exists.");
				}
			}
			throw ex;
		}
		return toDTO(user);
	}

	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
	}

	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	public UserDTO getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return toDTO(user);
	}

	public Page<UserResponseDTO> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(userMapper::toResponseDTO);
	}

	public PaginatedResponseDTO<UserDTO> getUsersPaginated(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		List<UserDTO> content = page.getContent().stream().map(userMapper::toDTO).collect(Collectors.toList());
		int pageNum = page.getNumber();
		int pageSize = page.getSize();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		boolean hasNext = page.hasNext();
		boolean hasPrevious = page.hasPrevious();
		String sort = pageable.getSort().toString();
		Object filter = null; // No filter logic for now
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null; // TODO: Generate if needed
		String previousPageUrl = null; // TODO: Generate if needed
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sort, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	public PaginatedResponseDTO<UserDTO> getUsersPaginatedAdvanced(String username, String email, Long roleId, int page,
			int size, String sortBy, String direction) {
		org.springframework.data.domain.Sort sort = direction.equalsIgnoreCase("desc")
				? org.springframework.data.domain.Sort.by(sortBy).descending()
				: org.springframework.data.domain.Sort.by(sortBy).ascending();
		org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size,
				sort);
		org.springframework.data.domain.Page<User> userPage = userRepository.findAll(pageable);
		java.util.List<User> filtered = userPage.getContent().stream()
				.filter(u -> username == null
						|| (u.getUsername() != null && u.getUsername().toLowerCase().contains(username.toLowerCase())))
				.filter(u -> email == null
						|| (u.getEmail() != null && u.getEmail().toLowerCase().contains(email.toLowerCase())))
				.filter(u -> roleId == null
						|| (u.getRoles() != null && u.getRoles().stream().anyMatch(r -> r.getRoleId().equals(roleId))))
				.collect(java.util.stream.Collectors.toList());
		java.util.List<UserDTO> content = filtered.stream().map(userMapper::toDTO)
				.collect(java.util.stream.Collectors.toList());
		int pageNum = userPage.getNumber();
		int pageSize = userPage.getSize();
		long totalElements = userPage.getTotalElements();
		int totalPages = userPage.getTotalPages();
		boolean hasNext = userPage.hasNext();
		boolean hasPrevious = userPage.hasPrevious();
		String sortStr = pageable.getSort().toString();
		Object filter = null;
		long firstElementIndex = pageNum * pageSize + 1;
		long lastElementIndex = firstElementIndex + content.size() - 1;
		String nextPageUrl = null;
		String previousPageUrl = null;
		return new PaginatedResponseDTO<>(content, pageNum, pageSize, totalElements, totalPages, hasNext, hasPrevious,
				sortStr, filter, firstElementIndex, lastElementIndex, nextPageUrl, previousPageUrl);
	}

	private UserDTO toDTO(User user) {
		Set<Long> roleIds = user.getRoles().stream().map(Role::getRoleId).collect(Collectors.toSet());
		return new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), roleIds, null);
	}

	public UserDTO updateUser(Long id, UserDTO dto) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
			user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
		}
		Set<Role> roles = new HashSet<>(roleRepository.findAllById(dto.getRoleIds()));
		user.setRoles(roles);
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
					throw new UserAlreadyExistsException("User with provided details already exists.");
				}
			}
			throw ex;
		}
		return toDTO(user);
	}
}