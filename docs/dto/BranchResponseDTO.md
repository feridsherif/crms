# BranchResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `BranchResponseDTO` class, which is used to transfer branch data from the server to the client, typically as a response object.

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
public class BranchResponseDTO {
```
- Declares the DTO class for branch response data.

---

## Fields

```java
@NotBlank
private String address;
@NotNull
private Long branchId;
@NotBlank
private String name;
@NotBlank
private String phone;
```
- `@NotBlank`: Ensures the field is not null or empty.
- `@NotNull`: Ensures the field is not null.
- `branchId`: Unique identifier for the branch.
- `address`, `name`, `phone`: Required fields for a branch.

---

## Constructors

```java
public BranchResponseDTO() { ... }
public BranchResponseDTO(@NotNull Long branchId, @NotBlank String name, @NotBlank String address, @NotBlank String phone) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

```java
public String getAddress() { ... }
public Long getBranchId() { ... }
public String getName() { ... }
public String getPhone() { ... }
public void setAddress(String address) { ... }
public void setBranchId(Long branchId) { ... }
public void setName(String name) { ... }
public void setPhone(String phone) { ... }
```
- Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending branch data in API responses.
- Enforces non-blank and non-null validation for all fields.
- Includes the unique branch ID.
