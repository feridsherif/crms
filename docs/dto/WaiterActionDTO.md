# WaiterActionDTO.java - Line-by-Line Explanation

This document provides a detailed explanation of the `WaiterActionDTO` class, which is used to transfer waiter action data (such as acknowledging or resolving a request) between client and server.

---

## Package Declaration

```java
package com.hilcoe.crms.dto;
```
- Declares the package for the DTO.

---

## Class Declaration

```java
public class WaiterActionDTO {
```
- Declares the DTO class for waiter action data.

---

## Fields

```java
private Long waiterId;
```
- `waiterId`: The ID of the waiter performing the action.

---

## Getters and Setters

Standard JavaBean getters and setters for the field.

---

## Summary
- Used for waiter action requests (e.g., acknowledge or resolve a request).
- Contains only the waiter ID relevant for the action.
