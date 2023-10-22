package com.example.mapper.seat;

import com.example.mapper.screening.ScreeningMapperInfrastructure;
import com.example.model.MovieScreening;
import com.example.model.Seat;
import com.example.persistence.seat.model.SeatJpaEntity;

public class SeatMapperInfrastructure {
    public static Seat toDomain(SeatJpaEntity seatJpaEntity){
        return new Seat(seatJpaEntity.getId(),
                        seatJpaEntity.getSeatStatus(),
                        seatJpaEntity.getRow(),
                        seatJpaEntity.getCol(),
                        ScreeningMapperInfrastructure.toDomain(seatJpaEntity.getMovieScreening()));
    }

    public static SeatJpaEntity toDataBase(Seat seat, MovieScreening movieScreening){
        return new SeatJpaEntity(seat.getId(),
                                 seat.getSeatStatus(),
                                 seat.getRow(),
                                 seat.getCol(),
                                 ScreeningMapperInfrastructure.toDataBase(movieScreening));
    }
}
