package com.example.mapper.seat;

import com.example.mapper.screening.ScreeningMapperApplication;
import com.example.model.MovieScreening;
import com.example.model.Seat;
import com.example.ports.input.screening.find_all_screenings_usecase.FindAllScreeningsResponse;
import com.example.ports.input.seat.find_all_by_screening_id_usecase.FindAllSeatsByScreeningIdResponse;
import com.example.ports.input.seat.find_all_seats_usecase.FindAllSeatsResponse;

public class SeatMapperApplication {
    public static FindAllSeatsResponse toResponseFindAll(Seat seat){
        return new FindAllSeatsResponse(seat.getId(),
                                        seat.getSeatStatus(),
                                        seat.getRow(),
                                        seat.getCol(),
                                        seat.getMovieScreening());
    }
    public static FindAllSeatsByScreeningIdResponse toResponseFindAllByScreeningId(Seat seat){
        return new FindAllSeatsByScreeningIdResponse(seat.getId(),
                seat.getSeatStatus(),
                seat.getRow(),
                seat.getCol(),
                seat.getMovieScreening().getId());
    }
}
