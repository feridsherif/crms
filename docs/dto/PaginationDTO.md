# PaginationDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `PaginationDTO` class, which is used to encapsulate pagination parameters for API requests.

---

## Package Declaration

```java
package com.hilcoe.crms.dto;
```
- Declares the package for the DTO.

---

## Class Declaration

```java
public class PaginationDTO {
```
- Declares the DTO class for pagination parameters.

---

## Fields

```java
private int page = 0;
private int size = 10;
private String sort;
```
- `page`: The current page number (zero-based, default 0).
- `size`: The number of items per page (default 10).
- `sort`: The sort criteria (e.g., "name,asc").

---

## Constructors

```java
public PaginationDTO() { ... }
public PaginationDTO(int page, int size, long totalElements, int totalPages) { ... }
```
- Default constructor initializes page and size to defaults.
- Parameterized constructor allows setting page and size (totalElements/totalPages are not stored by default).

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for specifying pagination and sorting in API requests.
- Contains only the essential parameters for pagination.
