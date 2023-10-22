package com.example.mapper.movie;

import com.example.mapper.genre.GenreMapperInfrastructure;
import com.example.model.Movie;
import com.example.persistence.movie.model.MovieJpaEntity;

import java.util.stream.Collectors;

public class MovieMapperInfrastructure {
    public static Movie toDomain(MovieJpaEntity movieJpaEntity){
        return new Movie(movieJpaEntity.getId(),
                         movieJpaEntity.getName(),
                         movieJpaEntity.getDuration(),
                         movieJpaEntity.getRating(),
                         movieJpaEntity.getPosterImage(),
                         movieJpaEntity.getGenres().stream()
                                                     .map(GenreMapperInfrastructure::toDomain)
                                                     .collect(Collectors.toSet()));
    }

    public static MovieJpaEntity toDataBase(Movie movie){
        return new MovieJpaEntity(
                                    movie.getId(),
                                    movie.getName(),
                                    movie.getDuration(),
                                    movie.getRating(),
                                    movie.getPosterImage(),
                                    movie.getGenres().stream()
                                            .map(GenreMapperInfrastructure::toDataBase)
                                            .collect(Collectors.toSet()));
    }

}
