# CategoryDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `CategoryDTO` class, which is used to transfer menu category data between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;

import jakarta.validation.constraints.NotBlank;
```
- Declares the package and imports validation annotations.

---

## Class Declaration

```java
public class CategoryDTO {
```
- Declares the DTO class for menu category data.

---

## Fields

```java
@NotBlank
public String description;
@NotBlank
public String name;
```
- `@NotBlank`: Ensures the field is not null or empty.
- `description`, `name`: Required fields for a menu category.

---

## Summary
- Used for menu category creation and update requests.
- Enforces non-blank validation for all fields.
- Fields are public for direct access.
