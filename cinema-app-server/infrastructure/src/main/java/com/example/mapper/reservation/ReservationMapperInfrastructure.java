package com.example.mapper.reservation;

import com.example.mapper.screening.ScreeningMapperInfrastructure;
import com.example.mapper.seat.SeatMapperInfrastructure;
import com.example.model.Reservation;
import com.example.persistence.reservation.model.ReservationJpaEntity;

import java.util.stream.Collectors;

public class ReservationMapperInfrastructure {
    public static Reservation toDomain(ReservationJpaEntity reservationJpaEntity){
        return new Reservation(reservationJpaEntity.getId(),
                reservationJpaEntity.getTag(),
                reservationJpaEntity.getTimestamp(),
                reservationJpaEntity.getUserEmail(),
                reservationJpaEntity.getNumOfTickets(),
                reservationJpaEntity.getTotalPrice(),
                reservationJpaEntity.getSelectedSeats().stream()
                        .map(SeatMapperInfrastructure::toDomain)
                        .collect(Collectors.toSet()),
                reservationJpaEntity.getReservationStatus(),
                ScreeningMapperInfrastructure.toDomain(reservationJpaEntity.getMovieScreening()));
    }
    public static ReservationJpaEntity toDataBase(Reservation reservation){
        return new ReservationJpaEntity(reservation.getId(),
                reservation.getTag(),
                reservation.getTimestamp(),
                reservation.getUserEmail(),
                reservation.getNumOfTickets(),
                reservation.getTotalPrice(),
                reservation.getSelectedSeats().stream()
                        .map(seat -> SeatMapperInfrastructure.toDataBase(seat, reservation.getMovieScreening()))
                        .collect(Collectors.toSet()),
                reservation.getReservationStatus(),
                ScreeningMapperInfrastructure.toDataBase(reservation.getMovieScreening()));
    }
}
