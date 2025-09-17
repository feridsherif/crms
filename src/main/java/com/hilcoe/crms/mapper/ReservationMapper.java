package com.hilcoe.crms.mapper;

import com.hilcoe.crms.entity.Reservation;
import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    Reservation toEntity(ReservationCreateDTO dto);

    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    Reservation toEntity(ReservationUpdateDTO dto);

    ReservationResponseDTO toResponseDTO(Reservation entity);
    ReservationFullResponseDTO toFullResponseDTO(Reservation entity);
}