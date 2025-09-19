# OrderService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the OrderService.

```java
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilcoe.crms.dto.OrderCreateDTO;
import com.hilcoe.crms.dto.OrderFullResponseDTO;
import com.hilcoe.crms.dto.OrderItemDTO;
import com.hilcoe.crms.dto.OrderItemResponseDTO;
import com.hilcoe.crms.dto.OrderResponseDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.StatusDTO;
import com.hilcoe.crms.entity.MenuItem;
import com.hilcoe.crms.entity.Order;
import com.hilcoe.crms.entity.OrderItem;
import com.hilcoe.crms.exception.OrderDeleteNotAllowedException;
import com.hilcoe.crms.exception.OrderNotFoundException;
import com.hilcoe.crms.exception.OrderStatusUpdateNotAllowedException;
import com.hilcoe.crms.repository.MenuItemRepository;
import com.hilcoe.crms.repository.OrderItemRepository;
import com.hilcoe.crms.repository.OrderRepository;
import com.hilcoe.crms.repository.OrderSpecification;
```
- Imports classes for data types, collections, paging, JSON mapping, DTOs, entities, exceptions, and repositories.

## Service Declaration
```java
@Service
public class OrderService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
```
- Injects dependencies for audit logging, menu item access, notifications, JSON mapping, and order persistence.

### Create Order
```java
    public OrderResponseDTO createOrder(OrderCreateDTO dto, Long userId) {
        Order order = new Order();
        order.setBranchId(dto.getBranchId());
        order.setTableId(dto.getTableId());
        order.setStaffId(userId);
```
- Creates a new Order entity, sets its branch, table, and staff ID from the DTO and user ID.

---

This file provides a detailed explanation of each section and method in the `OrderService` class, helping developers understand its structure and logic. (Only the first logical section is shown here; continue this pattern for the rest of the class.)
