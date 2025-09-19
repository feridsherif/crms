# WaiterRequestDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `WaiterRequestDTO` class, which is used to transfer waiter request data (such as calling a waiter) between client and server.

---

## Package and Imports

```java
package com.hilcoe.crms.dto;
import com.hilcoe.crms.entity.WaiterRequest;
import jakarta.validation.constraints.NotNull;
```
- Declares the package and imports validation annotations and the WaiterRequest entity for the request type enum.

---

## Class Declaration

```java
public class WaiterRequestDTO {
```
- Declares the DTO class for waiter request data.

---

## Fields

```java
@NotNull
private Long branchId;
private Long customerId;
@NotNull
private WaiterRequest.RequestType requestType;
@NotNull
private Long tableId;
```
- `branchId`: The branch where the request is made (required).
- `customerId`: The customer making the request (optional).
- `requestType`: The type of request (e.g., SERVICE, BILL) (required).
- `tableId`: The table for which the request is made (required).

---

## Constructors

```java
public WaiterRequestDTO() { ... }
public WaiterRequestDTO(@NotNull Long tableId, @NotNull Long branchId, @NotNull WaiterRequest.RequestType requestType, Long customerId) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for waiter request creation (e.g., customer calls waiter).
- Enforces validation for required fields.
- Contains all relevant info for a waiter request in the API.
