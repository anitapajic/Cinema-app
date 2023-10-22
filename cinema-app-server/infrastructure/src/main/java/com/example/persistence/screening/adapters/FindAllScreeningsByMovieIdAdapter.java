package com.example.persistence.screening.adapters;

import com.example.mapper.screening.ScreeningMapperInfrastructure;
import com.example.model.MovieScreening;
import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.ports.output.screening.FindAllScreeningsByMovieIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FindAllScreeningsByMovieIdAdapter implements FindAllScreeningsByMovieIdPort {
    private final MovieScreeningRepository movieScreeningRepository;

    @Override
    public List<MovieScreening> findByMovieId(Long movieId){
        return movieScreeningRepository.findByMovieId(movieId).stream()
                .map(ScreeningMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }
}
