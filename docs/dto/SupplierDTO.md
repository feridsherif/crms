# SupplierDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `SupplierDTO` class, which is used to transfer supplier data between client and server (for supplier creation and updates).

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
public class SupplierDTO {
```
- Declares the DTO class for supplier data.

---

## Fields

```java
@NotBlank
private String contact;
@NotBlank
private String name;
@NotBlank
private String phone;
@NotBlank
private String terms;
```
- `contact`: Contact information for the supplier (required).
- `name`: Name of the supplier (required).
- `phone`: Phone number of the supplier (required).
- `terms`: Terms of supply or contract (required).

---

## Constructors

```java
public SupplierDTO() { ... }
public SupplierDTO(String name, String contact, String phone, String terms) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for supplier creation and update requests.
- Enforces validation for all fields.
- Contains all relevant info for a supplier in the API.
