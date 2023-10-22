package com.example.mapper.screening;

import com.example.model.Movie;
import com.example.model.MovieScreening;
import com.example.ports.input.screening.find_all_by_movie_id_usecase.FindAllScreeningsByMovieIdResponse;
import com.example.ports.input.screening.find_all_screenings_by_date_usecase.FindAllScreeningsByDateResponse;
import com.example.ports.input.screening.find_all_screenings_usecase.FindAllScreeningsResponse;
import com.example.ports.input.screening.find_by_id_usecase.FindScreeningByIdResponse;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningRequest;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningResponse;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningRequest;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningResponse;

public class ScreeningMapperApplication {
    public static MovieScreening toDomain(SaveScreeningRequest saveScreeningRequest, Movie movie){
        return new MovieScreening(saveScreeningRequest.getDateTime(),
                                  movie,
                                  saveScreeningRequest.getPrice(),
                                  saveScreeningRequest.getSeatCol(),
                                  saveScreeningRequest.getSeatRow());
    }

    public static SaveScreeningResponse toResponse(MovieScreening movieScreening){
        return new SaveScreeningResponse(movieScreening.getId(),
                                         movieScreening.getDateTime(),
                                         movieScreening.getMovie(),
                                         movieScreening.getPrice(),
                                         movieScreening.getSeatCol(),
                                         movieScreening.getSeatRow());
    }

    public static FindScreeningByIdResponse toResponseFindById(MovieScreening movieScreening){
        return new FindScreeningByIdResponse(movieScreening.getId(),
                movieScreening.getDateTime(),
                movieScreening.getMovie(),
                movieScreening.getPrice(),
                movieScreening.getSeatCol(),
                movieScreening.getSeatRow());
    }

    public static FindAllScreeningsResponse toResponseFindAll(MovieScreening movieScreening){
        return new FindAllScreeningsResponse(movieScreening.getId(),
                movieScreening.getDateTime(),
                movieScreening.getMovie(),
                movieScreening.getPrice(),
                movieScreening.getSeatCol(),
                movieScreening.getSeatRow());
    }

    public static MovieScreening toDomainUpdate(UpdateScreeningRequest updateScreeningRequest, Movie movie){
        return new MovieScreening(updateScreeningRequest.getDateTime(),
                movie,
                updateScreeningRequest.getPrice(),
                updateScreeningRequest.getSeatCol(),
                updateScreeningRequest.getSeatRow());
    }

    public static UpdateScreeningResponse toResponseUpdate(MovieScreening movieScreening){
        return new UpdateScreeningResponse(movieScreening.getId(),
                movieScreening.getDateTime(),
                movieScreening.getMovie(),
                movieScreening.getPrice(),
                movieScreening.getSeatCol(),
                movieScreening.getSeatRow());
    }

    public static FindAllScreeningsByMovieIdResponse toResponseFindAllByMovieId(MovieScreening movieScreening){
        return new FindAllScreeningsByMovieIdResponse(movieScreening.getId(),
                movieScreening.getDateTime(),
                movieScreening.getMovie().getId(),
                movieScreening.getPrice(),
                movieScreening.getSeatCol(),
                movieScreening.getSeatRow());
    }

    public static FindAllScreeningsByDateResponse toResponseFindAllByDate(MovieScreening movieScreening){
        return new FindAllScreeningsByDateResponse(movieScreening.getId(),
                movieScreening.getDateTime(),
                movieScreening.getMovie().getId(),
                movieScreening.getPrice(),
                movieScreening.getSeatCol(),
                movieScreening.getSeatRow());
    }

}
