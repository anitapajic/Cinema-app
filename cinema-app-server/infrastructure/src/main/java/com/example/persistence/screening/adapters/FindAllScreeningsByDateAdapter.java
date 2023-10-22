package com.example.persistence.screening.adapters;

import com.example.mapper.screening.ScreeningMapperInfrastructure;
import com.example.model.MovieScreening;
import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.ports.output.screening.FindAllScreeningsByDatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllScreeningsByDateAdapter implements FindAllScreeningsByDatePort {
    private final MovieScreeningRepository movieScreeningRepository;

    @Override
    public List<MovieScreening> findAllByDate(LocalDate date){
        return movieScreeningRepository.findAllByDate(date).stream()
                .map(ScreeningMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }

}
