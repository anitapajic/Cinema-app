package com.example.persistence.movie.adapters;

import com.example.mapper.movie.MovieMapperInfrastructure;
import com.example.model.Movie;
import com.example.persistence.genre.repository.GenreRepository;
import com.example.persistence.movie.model.MovieJpaEntity;
import com.example.persistence.movie.repository.MovieRepository;
import com.example.ports.output.movie.SaveMoviePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveMovieAdapter implements SaveMoviePort {
    private final MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        MovieJpaEntity movieJpaEntity = MovieMapperInfrastructure.toDataBase(movie);
        MovieJpaEntity savedMovie = movieRepository.save(movieJpaEntity);
        return MovieMapperInfrastructure.toDomain(savedMovie);
    }

}
