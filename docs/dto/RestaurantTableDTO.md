# RestaurantTableDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `RestaurantTableDTO` class, which is used to transfer restaurant table data between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation annotations.

---

## Class Declaration

```java
public class RestaurantTableDTO {
```
- Declares the DTO class for restaurant table data.

---

## Fields

```java
@NotNull
private Long branchId;
@NotNull
private Integer capacity;
private String location;
private Long tableId;
@NotBlank
private String tableNumber;
```
- `branchId`: The branch this table belongs to (required).
- `capacity`: Number of seats at the table (required).
- `location`: Optional location/area description.
- `tableId`: Unique identifier for the table (optional, for updates).
- `tableNumber`: Table number or code (required).

---

## Constructors

```java
public RestaurantTableDTO() { ... }
public RestaurantTableDTO(Long tableId, Long branchId, String tableNumber, Integer capacity, String location) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for restaurant table creation and update requests.
- Enforces validation for required fields.
- Contains all relevant info for a restaurant table in the API.
