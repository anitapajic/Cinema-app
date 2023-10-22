package com.example.persistence.movie.adapters;

import com.example.exception.movie.MovieNotFoundException;
import com.example.mapper.movie.MovieMapperInfrastructure;
import com.example.model.Movie;
import com.example.persistence.genre.model.GenreJpaEntity;
import com.example.persistence.genre.repository.GenreRepository;
import com.example.persistence.movie.model.MovieJpaEntity;
import com.example.persistence.movie.repository.MovieRepository;
import com.example.ports.output.movie.UpdateMoviePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UpdateMovieAdapter implements UpdateMoviePort {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Override
    public Movie update(Long id, Movie movie){
        MovieJpaEntity updatedMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        Optional.ofNullable(movie.getName()).ifPresent(updatedMovie::setName);
        Optional.ofNullable(movie.getDuration()).ifPresent(updatedMovie::setDuration);
        Optional.ofNullable(movie.getRating()).ifPresent(updatedMovie::setRating);
        Optional.ofNullable(movie.getPosterImage()).ifPresent(updatedMovie::setPosterImage);
        Optional.ofNullable(movie.getGenres()).ifPresent(newGenres -> {
            Set<GenreJpaEntity> genres = newGenres.stream()
                    .map(x -> genreRepository.findById(x.getId()).orElseThrow())
                    .collect(Collectors.toSet());
            updatedMovie.setGenres(genres);
        });


        return MovieMapperInfrastructure.toDomain(movieRepository.save(updatedMovie));
    }
}
