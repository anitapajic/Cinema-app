package com.example.persistence.movie.adapters;

import com.example.mapper.movie.MovieMapperInfrastructure;
import com.example.model.Movie;
import com.example.persistence.movie.repository.MovieRepository;
import com.example.ports.output.movie.FindAllMoviesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FindAllMoviesAdapter implements FindAllMoviesPort {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAll(){
        return movieRepository.findAll().stream()
                .map(MovieMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }
}
