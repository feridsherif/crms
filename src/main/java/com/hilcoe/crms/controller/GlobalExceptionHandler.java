package com.hilcoe.crms.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hilcoe.crms.exception.BranchNotFoundException;
import com.hilcoe.crms.exception.CategoryNotFoundException;
import com.hilcoe.crms.exception.CustomerAlreadyExistsForUserException;
import com.hilcoe.crms.exception.CustomerNotFoundException;
import com.hilcoe.crms.exception.InventoryItemNotFoundException;
import com.hilcoe.crms.exception.MenuItemNotFoundException;
import com.hilcoe.crms.exception.OrderNotFoundException;
import com.hilcoe.crms.exception.PermissionNotFoundException;
import com.hilcoe.crms.exception.ReservationCancellationNotAllowedException;
import com.hilcoe.crms.exception.ReservationNotFoundException;
import com.hilcoe.crms.exception.ReservationStatusUpdateNotAllowedException;
import com.hilcoe.crms.exception.ReservationUpdateNotAllowedException;
import com.hilcoe.crms.exception.RestaurantTableNotFoundException;
import com.hilcoe.crms.exception.RoleNotFoundException;
import com.hilcoe.crms.exception.ShiftNotFoundException;
import com.hilcoe.crms.exception.StaffNotFoundException;
import com.hilcoe.crms.exception.SupplierNotFoundException;
import com.hilcoe.crms.exception.UserAlreadyExistsException;
import com.hilcoe.crms.exception.UserNotFoundException;
import com.hilcoe.crms.exception.WaiterRequestNotFound;

import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<String>> handleAccessDeniedException(AccessDeniedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.error("Access denied: " + ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleAllOtherExceptions(Exception ex) {
		logger.error("Unhandled exception", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse
				.error("An unexpected error occurred: " + ex.getClass().getSimpleName() + ": " + ex.getMessage()));
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiResponse.error("Authentication failed: " + ex.getMessage()));
	}

	@ExceptionHandler({ UserAlreadyExistsException.class, CustomerAlreadyExistsForUserException.class })
	public ResponseEntity<ApiResponse<String>> handleConflictException(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ApiResponse<String>> handleJwtException(JwtException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiResponse.error("Invalid or expired JWT: " + ex.getMessage()));
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse<String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(ApiResponse.error("Method not allowed: " + ex.getMessage()));
	}

	@ExceptionHandler({ ShiftNotFoundException.class, BranchNotFoundException.class, CategoryNotFoundException.class,
			CustomerNotFoundException.class, MenuItemNotFoundException.class, ReservationNotFoundException.class,
			StaffNotFoundException.class, InventoryItemNotFoundException.class, OrderNotFoundException.class,
			UserNotFoundException.class, PermissionNotFoundException.class, RoleNotFoundException.class,
			SupplierNotFoundException.class, WaiterRequestNotFound.class, RestaurantTableNotFoundException.class })
	public ResponseEntity<ApiResponse<String>> handleNotFoundException(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler({ ReservationCancellationNotAllowedException.class, ReservationUpdateNotAllowedException.class,
			ReservationStatusUpdateNotAllowedException.class })
	public ResponseEntity<ApiResponse<String>> handleReservationConflictException(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new java.util.HashMap<>();
		for (org.springframework.validation.FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(ApiResponse.error("Validation failed", errors));
	}
}