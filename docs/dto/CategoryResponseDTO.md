# CategoryResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `CategoryResponseDTO` class, which is used to transfer menu category data from the server to the client, typically as a response object.

---

## Package Declaration

```java
package com.hilcoe.crms.dto;
```
- Declares the package for the DTO.

---

## Class Declaration

```java
public class CategoryResponseDTO {
```
- Declares the DTO class for menu category response data.

---

## Fields

```java
public Long categoryId;
public String description;
public String name;
```
- `categoryId`: Unique identifier for the category.
- `description`: Description of the menu category.
- `name`: Name of the menu category.
- All fields are public for direct access.

---

## Summary
- Used for sending menu category data in API responses.
- Contains the unique category ID, name, and description.
- No validation or methods; simple data holder.
