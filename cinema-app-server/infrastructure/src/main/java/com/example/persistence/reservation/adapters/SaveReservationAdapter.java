package com.example.persistence.reservation.adapters;

import com.example.exception.reservation.ReservationNotSentOnMailException;
import com.example.mapper.reservation.ReservationMapperInfrastructure;
import com.example.model.Reservation;
import com.example.model.enums.ReservationStatus;
import com.example.model.enums.SeatStatus;
import com.example.persistence.reservation.model.ReservationJpaEntity;
import com.example.persistence.reservation.repository.ReservationRepository;
import com.example.persistence.seat.repository.SeatRepository;
import com.example.ports.output.reservation.SaveReservationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class SaveReservationAdapter implements SaveReservationPort {
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    @Override
    public Reservation save(Reservation reservation){
        ReservationJpaEntity reservationJpaEntity = ReservationMapperInfrastructure.toDataBase(reservation);
        Random rand = new Random();
        String tag = String.valueOf(rand.nextInt(10000000));
        reservationJpaEntity.setTag(tag);
        reservationJpaEntity.setReservationStatus(ReservationStatus.RESERVED);
        reservationJpaEntity.getSelectedSeats().stream()
                .peek(seat -> seat.setSeatStatus(SeatStatus.OCCUPIED))
                .forEach(seatRepository::save);
        reservationJpaEntity.setTimestamp(LocalDateTime.now());

        ReservationJpaEntity savedReservation = reservationRepository.save(reservationJpaEntity);
        return ReservationMapperInfrastructure.toDomain(savedReservation);
    }
}
