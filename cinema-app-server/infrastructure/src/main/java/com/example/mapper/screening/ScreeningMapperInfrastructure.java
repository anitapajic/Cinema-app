package com.example.mapper.screening;

import com.example.mapper.genre.GenreMapperInfrastructure;
import com.example.mapper.movie.MovieMapperInfrastructure;
import com.example.mapper.seat.SeatMapperInfrastructure;
import com.example.model.MovieScreening;
import com.example.persistence.screening.model.MovieScreeningJpaEntity;

import java.util.stream.Collectors;

public class ScreeningMapperInfrastructure {
    public static MovieScreening toDomain(MovieScreeningJpaEntity movieScreeningJpaEntity){
        return new MovieScreening(movieScreeningJpaEntity.getId(),
                movieScreeningJpaEntity.getDateTime(),
                MovieMapperInfrastructure.toDomain(movieScreeningJpaEntity.getMovie()),
                movieScreeningJpaEntity.getPrice(),
                movieScreeningJpaEntity.getSeatCol(),
                movieScreeningJpaEntity.getSeatRow());
    }

    public static MovieScreeningJpaEntity toDataBase(MovieScreening movieScreening){
        return new MovieScreeningJpaEntity(movieScreening.getId(),
                movieScreening.getDateTime(),
                MovieMapperInfrastructure.toDataBase(movieScreening.getMovie()),
                movieScreening.getPrice(),
                movieScreening.getSeatCol(),
                movieScreening.getSeatRow());
    }
}
