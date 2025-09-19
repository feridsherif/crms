# WaiterRequestResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `WaiterRequestResponseDTO` class, which is used to transfer waiter request data from the server to the client, typically as a response object.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import com.hilcoe.crms.entity.WaiterRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation annotations and the WaiterRequest entity for the request type enum.

---

## Class Declaration

```java
public class WaiterRequestResponseDTO {
```
- Declares the DTO class for waiter request response data.

---

## Fields

```java
@NotNull
private Long branchId;
private Long handledBy;
@NotNull
private Long requestId;
@NotNull
private WaiterRequest.RequestType requestType;
@NotBlank
private String status;
@NotNull
private Long tableId;
```
- `branchId`: The branch where the request was made (required).
- `handledBy`: The waiter/staff who handled the request (optional).
- `requestId`: Unique identifier for the request (required).
- `requestType`: The type of request (e.g., SERVICE, BILL) (required).
- `status`: The status of the request (e.g., PENDING, ACKNOWLEDGED, RESOLVED) (required).
- `tableId`: The table for which the request was made (required).

---

## Constructors

```java
public WaiterRequestResponseDTO() { ... }
public WaiterRequestResponseDTO(@NotNull Long requestId, @NotNull Long tableId, @NotNull Long branchId, @NotNull WaiterRequest.RequestType requestType, @NotBlank String status, Long handledBy) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending waiter request data in API responses.
- Enforces validation for all required fields.
- Contains all relevant info for a waiter request, including status and handler, in the API.
