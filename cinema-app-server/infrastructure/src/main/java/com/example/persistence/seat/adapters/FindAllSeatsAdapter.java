package com.example.persistence.seat.adapters;

import com.example.mapper.seat.SeatMapperInfrastructure;
import com.example.model.Seat;

import com.example.persistence.seat.repository.SeatRepository;
import com.example.ports.output.seat.FindAllSeatsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FindAllSeatsAdapter implements FindAllSeatsPort {
    private final SeatRepository seatRepository;

    @Override
    public List<Seat> findAll(){
        return seatRepository.findAll().stream()
                .map(SeatMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }
}
