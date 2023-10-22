package com.example.persistence.screening.adapters;

import com.example.exception.movie.MovieNotFoundException;
import com.example.mapper.movie.MovieMapperInfrastructure;
import com.example.mapper.screening.ScreeningMapperInfrastructure;
import com.example.model.Movie;
import com.example.model.MovieScreening;
import com.example.model.enums.SeatStatus;
import com.example.persistence.movie.model.MovieJpaEntity;
import com.example.persistence.movie.repository.MovieRepository;
import com.example.persistence.screening.model.MovieScreeningJpaEntity;
import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.persistence.seat.model.SeatJpaEntity;
import com.example.persistence.seat.repository.SeatRepository;
import com.example.ports.output.screening.SaveScreeningPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SaveMovieScreeningAdapter implements SaveScreeningPort {
    private final MovieScreeningRepository movieScreeningRepository;
    private final SeatRepository seatRepository;

    @Override
    public MovieScreening save(MovieScreening movieScreening) {
        MovieScreeningJpaEntity movieScreeningJpaEntity = ScreeningMapperInfrastructure.toDataBase(movieScreening);
        MovieScreeningJpaEntity savedMovieScreening = movieScreeningRepository.save(movieScreeningJpaEntity);
        List<SeatJpaEntity> seats = new ArrayList<>();

        int numRows = movieScreening.getSeatRow();
        int numCols = movieScreening.getSeatCol();

        for (int row = 1; row <= numRows; row++) {
            for (int col = 1; col <= numCols; col++) {
                SeatJpaEntity seat = new SeatJpaEntity(SeatStatus.AVAILABLE, row, col, savedMovieScreening);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);

        return ScreeningMapperInfrastructure.toDomain(savedMovieScreening);
    }
}
