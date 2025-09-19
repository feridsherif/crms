# MenuItemDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `MenuItemDTO` class, which is used to transfer menu item data between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports for validation and numeric types.

---

## Class Declaration

```java
public class MenuItemDTO {
```
- Declares the DTO class for menu item data.

---

## Fields

```java
@NotNull
private Long categoryId;
@NotBlank
private String description;
@NotNull
private Boolean isAvailable;
@NotBlank
private String name;
@NotNull
private BigDecimal price;
```
- `@NotNull` and `@NotBlank`: Enforce required fields.
- `categoryId`: The category this menu item belongs to.
- `description`: Description of the menu item.
- `isAvailable`: Whether the item is available for order.
- `name`: Name of the menu item.
- `price`: Price of the menu item.

---

## Constructors

```java
public MenuItemDTO() { ... }
public MenuItemDTO(Long categoryId, String name, String description, BigDecimal price, Boolean isAvailable) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for menu item creation and update requests.
- Enforces validation for all fields.
- Contains all relevant menu item info for the API.
