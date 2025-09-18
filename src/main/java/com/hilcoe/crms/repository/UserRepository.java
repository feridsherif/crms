package com.hilcoe.crms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hilcoe.crms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsername(String username);

	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
	List<User> findByRoleName(@Param("roleName") String roleName);

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions WHERE u.username = :username")
	Optional<User> findByUsernameWithRolesAndPermissions(@Param("username") String username);
}