package com.example.persistence.movie.adapters;

import com.example.exception.movie.MovieNotFoundException;
import com.example.mapper.movie.MovieMapperInfrastructure;
import com.example.model.Movie;
import com.example.persistence.movie.model.MovieJpaEntity;
import com.example.persistence.movie.repository.MovieRepository;
import com.example.ports.output.movie.FindMovieByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FindMovieByIdAdapter implements FindMovieByIdPort {
    private final MovieRepository movieRepository;

    @Override
    public Movie findById(Long id){
        MovieJpaEntity movieJpaEntity = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        return MovieMapperInfrastructure.toDomain(movieJpaEntity);
    }
}
