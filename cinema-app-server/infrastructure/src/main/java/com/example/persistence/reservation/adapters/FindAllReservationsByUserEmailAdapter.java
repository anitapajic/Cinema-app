package com.example.persistence.reservation.adapters;

import com.example.mapper.reservation.ReservationMapperInfrastructure;
import com.example.model.Reservation;
import com.example.persistence.reservation.repository.ReservationRepository;
import com.example.ports.output.reservation.FindAllReservationsByUserEmailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FindAllReservationsByUserEmailAdapter implements FindAllReservationsByUserEmailPort {
    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAllByUserEmail(String userEmail){
        return reservationRepository.findAllByUserEmail(userEmail).stream()
                .map(ReservationMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }

}
