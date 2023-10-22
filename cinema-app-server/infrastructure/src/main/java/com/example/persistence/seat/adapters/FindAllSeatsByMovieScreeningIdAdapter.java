package com.example.persistence.seat.adapters;

import com.example.mapper.seat.SeatMapperInfrastructure;
import com.example.model.Seat;
import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.persistence.seat.repository.SeatRepository;
import com.example.ports.output.seat.FindAllSeatsByMovieScreeningIdPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FindAllSeatsByMovieScreeningIdAdapter implements FindAllSeatsByMovieScreeningIdPort {
    private final SeatRepository seatRepository;

    @Transactional
    @Override
    public List<Seat> findAllByMovieScreeningId(Long id){
        return seatRepository.findByMovieScreeningIdOrderByIdAsc(id).stream()
                .map(SeatMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }

}
