package com.example.configuration;

import com.example.persistence.screening.adapters.*;
import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.persistence.seat.repository.SeatRepository;
import com.example.ports.input.screening.delete_screening_usecase.DeleteScreeningUsecase;
import com.example.ports.input.screening.find_all_by_movie_id_usecase.FindAllScreeningsByMovieIdUsecase;
import com.example.ports.input.screening.find_all_screenings_by_date_usecase.FindAllScreeningsByDateUsecase;
import com.example.ports.input.screening.find_all_screenings_usecase.FindAllScreeningsUsecase;
import com.example.ports.input.screening.find_by_id_usecase.FindScreeningByIdUsecase;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningUsecase;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningUsecase;
import com.example.ports.output.movie.FindMovieByIdPort;
import com.example.ports.output.screening.*;
import com.example.service.MovieScreeningService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieScreeningConfiguration {

    //======================================================================================
    // PORTS
    //======================================================================================

    @Bean
    public SaveScreeningPort saveScreeningPort(MovieScreeningRepository movieScreeningRepository, SeatRepository seatRepository){
        return new SaveMovieScreeningAdapter(movieScreeningRepository, seatRepository);
    }

    @Bean
    public FindScreeningByIdPort findScreeningByIdPort(MovieScreeningRepository movieScreeningRepository){
        return new FindScreeningByIdAdapter(movieScreeningRepository);
    }
    @Bean
    public FindAllScreeningsPort findAllScreeningsPort(MovieScreeningRepository movieScreeningRepository){
        return new FindAllScreeningsAdapter(movieScreeningRepository);
    }
    @Bean
    public DeleteScreeningPort deleteScreeningPort(MovieScreeningRepository movieScreeningRepository){
        return new DeleteMovieScreeningAdapter(movieScreeningRepository);
    }
    @Bean
    public UpdateScreeningPort updateScreeningPort(MovieScreeningRepository movieScreeningRepository){
        return new UpdateMovieScreeningAdapter(movieScreeningRepository);
    }
    @Bean
    public FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort(MovieScreeningRepository movieScreeningRepository){
        return new FindAllScreeningsByMovieIdAdapter(movieScreeningRepository);
    }
    @Bean
    public FindAllScreeningsByDatePort findAllScreeningsByDatePort(MovieScreeningRepository movieScreeningRepository){
        return new FindAllScreeningsByDateAdapter(movieScreeningRepository);
    }

    //======================================================================================
    // USE CASES
    //======================================================================================

    @Bean
    public SaveScreeningUsecase saveScreeningUsecase(SaveScreeningPort saveScreeningPort, FindScreeningByIdPort findScreeningByIdPort, FindAllScreeningsPort findAllScreeningsPort, DeleteScreeningPort deleteScreeningPort, UpdateScreeningPort updateScreeningPort, FindMovieByIdPort findMovieByIdPort, FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort, FindAllScreeningsByDatePort findAllScreeningsByDatePort){
        return new MovieScreeningService(saveScreeningPort, deleteScreeningPort, updateScreeningPort, findAllScreeningsPort, findScreeningByIdPort, findMovieByIdPort, findAllScreeningsByMovieIdPort, findAllScreeningsByDatePort);
    }
    @Bean
    FindScreeningByIdUsecase findScreeningByIdUsecase(SaveScreeningPort saveScreeningPort, FindScreeningByIdPort findScreeningByIdPort, FindAllScreeningsPort findAllScreeningsPort, DeleteScreeningPort deleteScreeningPort, UpdateScreeningPort updateScreeningPort, FindMovieByIdPort findMovieByIdPort, FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort, FindAllScreeningsByDatePort findAllScreeningsByDatePort){
        return new MovieScreeningService(saveScreeningPort, deleteScreeningPort, updateScreeningPort, findAllScreeningsPort, findScreeningByIdPort, findMovieByIdPort, findAllScreeningsByMovieIdPort, findAllScreeningsByDatePort);
    }
    @Bean
    FindAllScreeningsUsecase findAllScreeningsUsecase(SaveScreeningPort saveScreeningPort, FindScreeningByIdPort findScreeningByIdPort, FindAllScreeningsPort findAllScreeningsPort, DeleteScreeningPort deleteScreeningPort, UpdateScreeningPort updateScreeningPort, FindMovieByIdPort findMovieByIdPort, FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort, FindAllScreeningsByDatePort findAllScreeningsByDatePort){
        return new MovieScreeningService(saveScreeningPort, deleteScreeningPort, updateScreeningPort, findAllScreeningsPort, findScreeningByIdPort, findMovieByIdPort, findAllScreeningsByMovieIdPort, findAllScreeningsByDatePort);
    }
    @Bean
    DeleteScreeningUsecase deleteScreeningUsecase(SaveScreeningPort saveScreeningPort, FindScreeningByIdPort findScreeningByIdPort, FindAllScreeningsPort findAllScreeningsPort, DeleteScreeningPort deleteScreeningPort, UpdateScreeningPort updateScreeningPort, FindMovieByIdPort findMovieByIdPort, FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort, FindAllScreeningsByDatePort findAllScreeningsByDatePort){
        return new MovieScreeningService(saveScreeningPort, deleteScreeningPort, updateScreeningPort, findAllScreeningsPort, findScreeningByIdPort, findMovieByIdPort, findAllScreeningsByMovieIdPort, findAllScreeningsByDatePort);
    }
    @Bean
    UpdateScreeningUsecase updateScreeningUsecase(SaveScreeningPort saveScreeningPort, FindScreeningByIdPort findScreeningByIdPort, FindAllScreeningsPort findAllScreeningsPort, DeleteScreeningPort deleteScreeningPort, UpdateScreeningPort updateScreeningPort, FindMovieByIdPort findMovieByIdPort, FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort, FindAllScreeningsByDatePort findAllScreeningsByDatePort){
        return new MovieScreeningService(saveScreeningPort, deleteScreeningPort, updateScreeningPort, findAllScreeningsPort, findScreeningByIdPort, findMovieByIdPort, findAllScreeningsByMovieIdPort, findAllScreeningsByDatePort);
    }
    @Bean
    FindAllScreeningsByMovieIdUsecase findAllScreeningsByMovieIdUsecase(SaveScreeningPort saveScreeningPort, FindScreeningByIdPort findScreeningByIdPort, FindAllScreeningsPort findAllScreeningsPort, DeleteScreeningPort deleteScreeningPort, UpdateScreeningPort updateScreeningPort, FindMovieByIdPort findMovieByIdPort, FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort, FindAllScreeningsByDatePort findAllScreeningsByDatePort){
        return new MovieScreeningService(saveScreeningPort, deleteScreeningPort, updateScreeningPort, findAllScreeningsPort, findScreeningByIdPort, findMovieByIdPort, findAllScreeningsByMovieIdPort, findAllScreeningsByDatePort);
    }
    @Bean
    FindAllScreeningsByDateUsecase findAllScreeningsByDateUsecase(SaveScreeningPort saveScreeningPort, FindScreeningByIdPort findScreeningByIdPort, FindAllScreeningsPort findAllScreeningsPort, DeleteScreeningPort deleteScreeningPort, UpdateScreeningPort updateScreeningPort, FindMovieByIdPort findMovieByIdPort, FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort, FindAllScreeningsByDatePort findAllScreeningsByDatePort){
        return new MovieScreeningService(saveScreeningPort, deleteScreeningPort, updateScreeningPort, findAllScreeningsPort, findScreeningByIdPort, findMovieByIdPort, findAllScreeningsByMovieIdPort, findAllScreeningsByDatePort);
    }
}
