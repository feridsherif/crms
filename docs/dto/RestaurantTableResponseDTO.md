# RestaurantTableResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `RestaurantTableResponseDTO` class, which is used to transfer restaurant table data from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import com.hilcoe.crms.entity.RestaurantTable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation annotations and the RestaurantTable entity for status enum.

---

## Class Declaration

```java
public class RestaurantTableResponseDTO {
```
- Declares the DTO class for restaurant table response data.

---

## Fields

```java
@NotNull
private Long branchId;
@NotNull
private Integer capacity;
private String location;
@NotBlank
private RestaurantTable.RestaurantTableStatus status;
@NotNull
private Long tableId;
@NotBlank
private String tableNumber;
```
- `branchId`: The branch this table belongs to (required).
- `capacity`: Number of seats at the table (required).
- `location`: Optional location/area description.
- `status`: Current status of the table (e.g., AVAILABLE, OCCUPIED) (required).
- `tableId`: Unique identifier for the table (required).
- `tableNumber`: Table number or code (required).

---

## Constructors

```java
public RestaurantTableResponseDTO() { ... }
public RestaurantTableResponseDTO(Long tableId, Long branchId, String tableNumber, Integer capacity, String location, RestaurantTable.RestaurantTableStatus status) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending restaurant table data in API responses.
- Enforces validation for required fields.
- Contains all relevant info for a restaurant table, including status, in the API.
