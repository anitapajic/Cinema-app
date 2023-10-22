package com.example.persistence.reservation.adapters;

import com.example.exception.reservation.ReservationNotFoundException;
import com.example.mapper.reservation.ReservationMapperInfrastructure;
import com.example.model.Reservation;
import com.example.model.enums.ReservationStatus;
import com.example.model.enums.SeatStatus;
import com.example.persistence.reservation.model.ReservationJpaEntity;
import com.example.persistence.reservation.repository.ReservationRepository;
import com.example.persistence.seat.repository.SeatRepository;
import com.example.ports.output.reservation.CancelReservationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelReservationAdapter implements CancelReservationPort {
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    @Override
    public Reservation cancel(Long id){
        ReservationJpaEntity reservationJpaEntity = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        reservationJpaEntity.setReservationStatus(ReservationStatus.CANCELED);
        reservationJpaEntity.getSelectedSeats().stream()
                .peek(seat -> seat.setSeatStatus(SeatStatus.AVAILABLE))
                .forEach(seatRepository::save);
        reservationRepository.save(reservationJpaEntity);

        return ReservationMapperInfrastructure.toDomain(reservationJpaEntity);
    }
}
