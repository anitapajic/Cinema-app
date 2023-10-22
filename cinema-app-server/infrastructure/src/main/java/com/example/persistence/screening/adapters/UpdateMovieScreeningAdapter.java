package com.example.persistence.screening.adapters;

import com.example.exception.screening.ScreeningNotFoundException;
import com.example.mapper.movie.MovieMapperInfrastructure;
import com.example.mapper.screening.ScreeningMapperInfrastructure;
import com.example.model.MovieScreening;
import com.example.persistence.screening.model.MovieScreeningJpaEntity;
import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.ports.output.screening.UpdateScreeningPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UpdateMovieScreeningAdapter implements UpdateScreeningPort {
    private final MovieScreeningRepository movieScreeningRepository;

    @Override
    public MovieScreening update(Long id, MovieScreening movieScreening){
        MovieScreeningJpaEntity updatedMovieScreening = movieScreeningRepository.findById(id)
                .orElseThrow(() -> new ScreeningNotFoundException(id));

        Optional.ofNullable(movieScreening.getDateTime()).ifPresent(updatedMovieScreening::setDateTime);
        //TODO : check this
        Optional.ofNullable(movieScreening.getMovie())
                .map(MovieMapperInfrastructure::toDataBase)
                .ifPresent(updatedMovieScreening::setMovie);

        Optional.ofNullable(movieScreening.getPrice()).ifPresent(updatedMovieScreening::setPrice);
        Optional.ofNullable(movieScreening.getSeatCol()).ifPresent(updatedMovieScreening::setSeatCol);
        Optional.ofNullable(movieScreening.getSeatRow()).ifPresent(updatedMovieScreening::setSeatRow);

        return ScreeningMapperInfrastructure.toDomain(movieScreeningRepository.save(updatedMovieScreening));
    }
}
