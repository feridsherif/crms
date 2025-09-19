# BranchDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `BranchDTO` class, which is used to transfer branch data between client and server.

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
public class BranchDTO {
```
- Declares the DTO class for branch data.

---

## Fields

```java
@NotBlank
private String address;
@NotBlank
private String name;
@NotBlank
private String phone;
```
- `@NotBlank`: Ensures the field is not null or empty.
- `address`, `name`, `phone`: Required fields for a branch.

---

## Constructors

```java
public BranchDTO() { ... }
public BranchDTO(String name, String address, String phone) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

```java
public String getAddress() { ... }
public String getName() { ... }
public String getPhone() { ... }
public void setAddress(String address) { ... }
public void setName(String name) { ... }
public void setPhone(String phone) { ... }
```
- Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for branch creation and update requests.
- Enforces non-blank validation for all fields.
