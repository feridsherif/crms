# InventoryReportDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `InventoryReportDTO` class, which is used to transfer inventory report data, including item details, stock movements, and supplier info.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;

import java.math.BigDecimal;
import java.util.List;
```
- Declares the package and imports for numeric and list types.

---

## Class Declaration

```java
public class InventoryReportDTO {
```
- Declares the DTO class for inventory report data.

---

## Nested Classes

### ItemReport
```java
public static class ItemReport {
    public Long id;
    public BigDecimal itemValue;
    public String name;
    public BigDecimal quantity;
    public List<StockMovementInfo> recentMovements;
    public SupplierInfo supplier;
    public BigDecimal threshold;
    public String unit;
}
```
- Represents a single inventory item in the report.
- Includes item ID, value, name, quantity, recent stock movements, supplier info, threshold, and unit.

### StockMovementInfo
```java
public static class StockMovementInfo {
    public String date;
    public BigDecimal quantityChange;
    public String reason;
}
```
- Represents a stock movement (change in quantity), with date, amount, and reason.

### SupplierInfo
```java
public static class SupplierInfo {
    public String contact;
    public Long id;
    public String name;
    public String phone;
    public String terms;
}
```
- Represents supplier details for an inventory item.

---

## Fields

```java
public List<ItemReport> items;
public List<ItemReport> lowStockItems;
public BigDecimal totalInventoryValue;
```
- `items`: List of all inventory items in the report.
- `lowStockItems`: List of items below their threshold.
- `totalInventoryValue`: Total value of all inventory items (if available).

---

## Summary
- Used for generating and transferring inventory reports.
- Contains nested classes for detailed item, movement, and supplier info.
- Used in reporting endpoints for inventory management.
