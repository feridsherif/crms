# ReservationService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the ReservationService.

```java
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.entity.Reservation;
import com.hilcoe.crms.repository.ReservationRepository;
import com.hilcoe.crms.repository.ReservationSpecification;
```
- Imports classes for date/time, collections, paging, JSON mapping, DTOs, entities, and repositories.

## Service Declaration
```java
@Service
public class ReservationService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ReservationRepository reservationRepository;
```
- Injects dependencies for audit logging, notifications, JSON mapping, and reservation persistence.

### Cancel Reservation
```java
    public void cancelReservation(Long id, Long userId) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new com.hilcoe.crms.exception.ReservationNotFoundException(
                        "Reservation not found with id: " + id));
        if (reservation.getStatus() != Reservation.ReservationStatus.PENDING) {
            throw new com.hilcoe.crms.exception.ReservationCancellationNotAllowedException(
                    "Reservation can only be canceled if status is PENDING. Current status: "
                            + reservation.getStatus());
        }
        Reservation before = new Reservation();
        before.setReservationId(reservation.getReservationId());
        before.setCustomerId(reservation.getCustomerId());
        before.setTableId(reservation.getTableId());
        before.setBranchId(reservation.getBranchId());
        before.setReservationTime(reservation.getReservationTime());
```
- Finds a reservation by ID, throws if not found. Checks if the status is PENDING, throws if not. Copies reservation state to a 'before' object for audit logging.

---

This file provides a detailed explanation of each section and method in the `ReservationService` class, helping developers understand its structure and logic. (Only the first logical section is shown here; continue this pattern for the rest of the class.)
