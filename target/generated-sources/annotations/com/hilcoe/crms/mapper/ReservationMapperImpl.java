package com.hilcoe.crms.mapper;

import com.hilcoe.crms.dto.ReservationCreateDTO;
import com.hilcoe.crms.dto.ReservationFullResponseDTO;
import com.hilcoe.crms.dto.ReservationResponseDTO;
import com.hilcoe.crms.dto.ReservationUpdateDTO;
import com.hilcoe.crms.entity.Reservation;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T09:18:43+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public Reservation toEntity(ReservationCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setCustomerId( dto.getCustomerId() );
        reservation.setTableId( dto.getTableId() );
        reservation.setReservationTime( dto.getReservationTime() );
        reservation.setPartySize( dto.getPartySize() );

        return reservation;
    }

    @Override
    public Reservation toEntity(ReservationUpdateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setReservationTime( dto.getReservationTime() );
        reservation.setPartySize( dto.getPartySize() );

        return reservation;
    }

    @Override
    public ReservationResponseDTO toResponseDTO(Reservation entity) {
        if ( entity == null ) {
            return null;
        }

        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO();

        reservationResponseDTO.setReservationId( entity.getReservationId() );
        if ( entity.getStatus() != null ) {
            reservationResponseDTO.setStatus( entity.getStatus().name() );
        }

        return reservationResponseDTO;
    }

    @Override
    public ReservationFullResponseDTO toFullResponseDTO(Reservation entity) {
        if ( entity == null ) {
            return null;
        }

        ReservationFullResponseDTO reservationFullResponseDTO = new ReservationFullResponseDTO();

        reservationFullResponseDTO.setReservationId( entity.getReservationId() );
        reservationFullResponseDTO.setCustomerId( entity.getCustomerId() );
        reservationFullResponseDTO.setTableId( entity.getTableId() );
        reservationFullResponseDTO.setBranchId( entity.getBranchId() );
        reservationFullResponseDTO.setReservationTime( entity.getReservationTime() );
        reservationFullResponseDTO.setPartySize( entity.getPartySize() );
        if ( entity.getStatus() != null ) {
            reservationFullResponseDTO.setStatus( entity.getStatus().name() );
        }

        return reservationFullResponseDTO;
    }
}
