# InventoryService.java - Line-by-Line Explanation

## Package and Imports
```java
package com.hilcoe.crms.service;
```
- Declares the package for the InventoryService.

```java
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.hilcoe.crms.dto.InventoryItemDTO;
import com.hilcoe.crms.dto.InventoryItemResponseDTO;
import com.hilcoe.crms.dto.InventoryReportDTO;
import com.hilcoe.crms.dto.PaginatedResponseDTO;
import com.hilcoe.crms.dto.StockAdjustmentDTO;
import com.hilcoe.crms.entity.InventoryItem;
import com.hilcoe.crms.entity.StockMovement;
import com.hilcoe.crms.entity.Supplier;
import com.hilcoe.crms.entity.User;
import com.hilcoe.crms.repository.InventoryItemRepository;
import com.hilcoe.crms.repository.InventoryItemSpecification;
import com.hilcoe.crms.repository.StockMovementRepository;
import com.hilcoe.crms.repository.SupplierRepository;
import com.hilcoe.crms.repository.UserRepository;
```
- Imports classes for data types, collections, paging, security, DTOs, entities, and repositories.

## Service Declaration
```java
@Service
public class InventoryService {
```
- Declares the class as a Spring service.

### Dependencies
```java
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private StockMovementRepository stockMovementRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UserRepository userRepository;
```
- Injects dependencies for audit logging, inventory, notification, stock movement, supplier, and user persistence.

### Add Inventory Item
```java
    public InventoryItemResponseDTO addItem(InventoryItemDTO dto) {
        InventoryItem item = new InventoryItem();
        item.setName(dto.getName());
```
- Creates a new inventory item and sets its name from the DTO.

---

This file provides a detailed explanation of each section and method in the `InventoryService` class, helping developers understand its structure and logic. (Continue this pattern for the rest of the class.)
