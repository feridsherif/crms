# PaginatedResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `PaginatedResponseDTO` class, which is a generic wrapper for paginated API responses.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import java.util.List;
```
- Declares the package and imports the List type for content.

---

## Class Declaration

```java
public class PaginatedResponseDTO<T> {
```
- Declares a generic DTO class for paginated responses.
- `<T>` allows this class to wrap any type of content.

---

## Fields

```java
private List<T> content;
private Object filter;
private long firstElementIndex;
private boolean hasNext;
private boolean hasPrevious;
private long lastElementIndex;
private String nextPageUrl;
private int page;
private String previousPageUrl;
private int size;
private String sort;
private long totalElements;
private int totalPages;
```
- `content`: The list of items for the current page.
- `filter`: The filter object used for the query (if any).
- `firstElementIndex`, `lastElementIndex`: Indexes of the first and last elements on this page.
- `hasNext`, `hasPrevious`: Flags for next/previous page availability.
- `nextPageUrl`, `previousPageUrl`: URLs for navigation (if provided).
- `page`: Current page number (zero-based).
- `size`: Number of items per page.
- `sort`: Sorting criteria.
- `totalElements`: Total number of items across all pages.
- `totalPages`: Total number of pages.

---

## Constructors

```java
public PaginatedResponseDTO() { ... }
public PaginatedResponseDTO(List<T> content, int page, int size, long totalElements, int totalPages) { ... }
public PaginatedResponseDTO(List<T> content, int page, int size, long totalElements, int totalPages, boolean hasNext, boolean hasPrevious, String sort, Object filter, long firstElementIndex, long lastElementIndex, String nextPageUrl, String previousPageUrl) { ... }
```
- Default and parameterized constructors for flexible instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields, allowing encapsulated access and modification.

---

## Summary
- Used for paginated API responses.
- Generic, reusable for any type of content.
- Contains pagination metadata, navigation, and filter info.
