# ReservationMapper Documentation

This document provides a line-by-line explanation of the `ReservationMapper` interface, which is responsible for mapping between `Reservation` entities and their corresponding DTOs in the CRMS system.

---

```java
package com.hilcoe.crms.mapper;
```
*Declares the package for the mapper class.*

```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.entity.Reservation;
```
*Imports MapStruct annotations and relevant DTO/entity classes.*

---

```java
@Mapper(componentModel = "spring")
```
*Indicates this interface is a MapStruct mapper and should be managed as a Spring bean.*

```java
public interface ReservationMapper {
```
*Defines the ReservationMapper interface.*

```java
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
```
*Provides a static instance for manual usage if needed (not required when using Spring injection).* 

---

### Mapping Methods

```java
    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    Reservation toEntity(ReservationCreateDTO dto);
```
*Maps a `ReservationCreateDTO` to a `Reservation` entity, ignoring fields that are set elsewhere (e.g., by the service or persistence layer).* 

```java
    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    Reservation toEntity(ReservationUpdateDTO dto);
```
*Maps a `ReservationUpdateDTO` to a `Reservation` entity, ignoring the reservationId and branchId fields.*

```java
    ReservationFullResponseDTO toFullResponseDTO(Reservation entity);
```
*Maps a `Reservation` entity to a `ReservationFullResponseDTO` for detailed API responses.*

```java
    ReservationResponseDTO toResponseDTO(Reservation entity);
```
*Maps a `Reservation` entity to a `ReservationResponseDTO` for summary API responses.*

```
}
```
*End of the interface definition.*

---

## Summary
- **Purpose:** Converts between `Reservation` entity and its DTOs for service and controller layers.
- **Framework:** Uses MapStruct for compile-time mapping generation.
- **Usage:** Used by service/controller layers to simplify data transfer and API responses.

# ReservationMapper.java - Line-by-Line Explanation

```java
package com.hilcoe.crms.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.entity.Reservation;
```
- Imports for MapStruct, DTOs, and entity.

```java
@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
```
- Declares a MapStruct mapper for Spring.

```java
    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    Reservation toEntity(ReservationCreateDTO dto);
```
- Maps ReservationCreateDTO to Reservation entity, ignoring ID, status, and branchId.

```java
    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    Reservation toEntity(ReservationUpdateDTO dto);
```
- Maps ReservationUpdateDTO to Reservation entity, ignoring ID and branchId.

```java
    ReservationFullResponseDTO toFullResponseDTO(Reservation entity);
```
- Maps Reservation entity to a full response DTO.

```java
    ReservationResponseDTO toResponseDTO(Reservation entity);
}
```
- Maps Reservation entity to a basic response DTO.