package com.example.persistence.screening.adapters;

import com.example.mapper.screening.ScreeningMapperInfrastructure;
import com.example.model.MovieScreening;
import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.ports.output.screening.FindAllScreeningsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FindAllScreeningsAdapter implements FindAllScreeningsPort {
    private final MovieScreeningRepository movieScreeningRepository;

    @Override
    public List<MovieScreening> findAll(){
        return movieScreeningRepository.findAll().stream()
                .map(ScreeningMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }
}
