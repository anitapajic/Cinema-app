package com.example.persistence.screening.adapters;

import com.example.exception.screening.ScreeningNotFoundException;
import com.example.mapper.screening.ScreeningMapperInfrastructure;
import com.example.model.MovieScreening;
import com.example.persistence.screening.model.MovieScreeningJpaEntity;
import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.ports.output.screening.FindScreeningByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FindScreeningByIdAdapter implements FindScreeningByIdPort {
    private final MovieScreeningRepository movieScreeningRepository;

    @Override
    public MovieScreening findById(Long id){
        MovieScreeningJpaEntity movieScreeningJpaEntity = movieScreeningRepository.findById(id)
                .orElseThrow(() -> new ScreeningNotFoundException(id));
        return ScreeningMapperInfrastructure.toDomain(movieScreeningJpaEntity);
    }



}