# SupplierResponseDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `SupplierResponseDTO` class, which is used to transfer supplier data from the server to the client, typically as a response object.

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
public class SupplierResponseDTO {
```
- Declares the DTO class for supplier response data.

---

## Fields

```java
@NotBlank
private String contact;
@NotBlank
private String name;
@NotBlank
private String phone;
@NotNull
private Long supplierId;
@NotBlank
private String terms;
```
- `contact`: Contact information for the supplier (required).
- `name`: Name of the supplier (required).
- `phone`: Phone number of the supplier (required).
- `supplierId`: Unique identifier for the supplier (required).
- `terms`: Terms of supply or contract (required).

---

## Constructors

```java
public SupplierResponseDTO() { ... }
public SupplierResponseDTO(@NotNull Long supplierId, @NotBlank String name, @NotBlank String contact, @NotBlank String phone, @NotBlank String terms) { ... }
```
- Default and parameterized constructors for easy instantiation.

---

## Getters and Setters

Standard JavaBean getters and setters for all fields.

---

## Summary
- Used for sending supplier data in API responses.
- Enforces validation for all fields.
- Contains all relevant info for a supplier, including the unique ID, in the API.
