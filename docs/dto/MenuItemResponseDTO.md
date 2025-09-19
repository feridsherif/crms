# MenuItemResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `MenuItemResponseDTO` class, which is used to transfer menu item data from the server to the client, typically as a response object.

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
public class MenuItemResponseDTO {
```
- Declares the DTO class for menu item response data.

---

## Fields

```java
@NotNull
private Long categoryId;
@NotBlank
private String description;
@NotNull
private Boolean isAvailable;
@NotNull
private Long menuItemId;
@NotBlank
private String name;
@NotNull
private BigDecimal price;
```
- `@NotNull` and `@NotBlank`: Enforce required fields.
- `categoryId`: The category this menu item belongs to.
- `description`: Description of the menu item.
- `isAvailable`: Whether the item is available for order.
- `menuItemId`: Unique identifier for the menu item.
- `name`: Name of the menu item.
- `price`: Price of the menu item.

---

## Constructors

```java
public MenuItemResponseDTO() { ... }
public MenuItemResponseDTO(@NotNull Long menuItemId, @NotNull Long categoryId, @NotBlank String name, @NotBlank String description, @NotNull BigDecimal price, @NotNull Boolean isAvailable) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending menu item data in API responses.
- Enforces validation for all fields.
- Contains the unique menu item ID and all relevant info for the API.
