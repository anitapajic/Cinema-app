package com.example.configuration;

import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.persistence.seat.adapters.FindAllSeatsAdapter;
import com.example.persistence.seat.adapters.FindAllSeatsByMovieScreeningIdAdapter;
import com.example.persistence.seat.repository.SeatRepository;
import com.example.ports.input.seat.find_all_by_screening_id_usecase.FindAllSeatsByScreeningIdUsecase;
import com.example.ports.input.seat.find_all_seats_usecase.FindAllSeatsUsecase;
import com.example.ports.output.seat.FindAllSeatsByMovieScreeningIdPort;
import com.example.ports.output.seat.FindAllSeatsPort;
import com.example.service.SeatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeatConfiguration {

    //======================================================================================
    // PORTS
    //======================================================================================

    @Bean
    public FindAllSeatsPort findAllSeatsPort(SeatRepository seatRepository){
        return new FindAllSeatsAdapter(seatRepository);
    }
    @Bean
    public FindAllSeatsByMovieScreeningIdPort findAllSeatsByMovieScreeningIdPort(SeatRepository seatRepository){
        return new FindAllSeatsByMovieScreeningIdAdapter(seatRepository);
    }

    //======================================================================================
    // USE CASES
    //======================================================================================

    @Bean
    FindAllSeatsUsecase findAllSeatsUsecase(FindAllSeatsPort findAllSeatsPort, FindAllSeatsByMovieScreeningIdPort findAllSeatsByMovieScreeningIdPort){
        return new SeatService(findAllSeatsPort, findAllSeatsByMovieScreeningIdPort);
    }
    @Bean
    FindAllSeatsByScreeningIdUsecase findAllSeatsByScreeningIdUsecase(FindAllSeatsPort findAllSeatsPort, FindAllSeatsByMovieScreeningIdPort findAllSeatsByMovieScreeningIdPort){
        return new SeatService(findAllSeatsPort, findAllSeatsByMovieScreeningIdPort);
    }
}
