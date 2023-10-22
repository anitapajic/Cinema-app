package com.example.service;

import com.example.mapper.screening.ScreeningMapperApplication;
import com.example.model.Movie;
import com.example.model.MovieScreening;
import com.example.ports.input.screening.delete_screening_usecase.DeleteScreeningUsecase;
import com.example.ports.input.screening.find_all_by_movie_id_usecase.FindAllScreeningsByMovieIdResponse;
import com.example.ports.input.screening.find_all_by_movie_id_usecase.FindAllScreeningsByMovieIdUsecase;
import com.example.ports.input.screening.find_all_screenings_by_date_usecase.FindAllScreeningsByDateResponse;
import com.example.ports.input.screening.find_all_screenings_by_date_usecase.FindAllScreeningsByDateUsecase;
import com.example.ports.input.screening.find_all_screenings_usecase.FindAllScreeningsResponse;
import com.example.ports.input.screening.find_all_screenings_usecase.FindAllScreeningsUsecase;
import com.example.ports.input.screening.find_by_id_usecase.FindScreeningByIdResponse;
import com.example.ports.input.screening.find_by_id_usecase.FindScreeningByIdUsecase;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningRequest;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningResponse;
import com.example.ports.input.screening.save_screening_usecase.SaveScreeningUsecase;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningRequest;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningResponse;
import com.example.ports.input.screening.update_screening_usecase.UpdateScreeningUsecase;
import com.example.ports.output.movie.FindMovieByIdPort;
import com.example.ports.output.screening.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieScreeningService implements SaveScreeningUsecase,
                                              DeleteScreeningUsecase,
                                              UpdateScreeningUsecase,
                                              FindScreeningByIdUsecase,
                                              FindAllScreeningsUsecase,
                                              FindAllScreeningsByMovieIdUsecase,
                                              FindAllScreeningsByDateUsecase {
    private final SaveScreeningPort saveScreeningPort;
    private final DeleteScreeningPort deleteScreeningPort;
    private final UpdateScreeningPort updateScreeningPort;
    private final FindAllScreeningsPort findAllScreeningsPort;
    private final FindScreeningByIdPort findScreeningByIdPort;
    private final FindMovieByIdPort findMovieByIdPort;
    private final FindAllScreeningsByMovieIdPort findAllScreeningsByMovieIdPort;
    private final FindAllScreeningsByDatePort findAllScreeningsByDatePort;

    @Override
    public SaveScreeningResponse save(SaveScreeningRequest saveScreeningRequest) {
        Movie movie = findMovieByIdPort.findById(saveScreeningRequest.getMovieId());
        MovieScreening movieScreening = ScreeningMapperApplication.toDomain(saveScreeningRequest, movie);
        MovieScreening savedMovieScreening = saveScreeningPort.save(movieScreening);
        return ScreeningMapperApplication.toResponse(savedMovieScreening);
    }

    @Override
    public FindScreeningByIdResponse findById(Long id){
        MovieScreening movieScreening = findScreeningByIdPort.findById(id);
        return ScreeningMapperApplication.toResponseFindById(movieScreening);
    }

    @Override
    public List<FindAllScreeningsResponse> findAll(){
        return findAllScreeningsPort.findAll().stream()
                .map(ScreeningMapperApplication::toResponseFindAll)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id){
        deleteScreeningPort.deleteById(id);
    }

    @Override
    public UpdateScreeningResponse update(Long id, UpdateScreeningRequest updateScreeningRequest){
        MovieScreening movieScreening;
        if(updateScreeningRequest.getMovieId()!=null){
            Movie movie = findMovieByIdPort.findById(updateScreeningRequest.getMovieId());
            movieScreening = ScreeningMapperApplication.toDomainUpdate(updateScreeningRequest, movie);
        } else {
            movieScreening = ScreeningMapperApplication.toDomainUpdate(updateScreeningRequest, null);
        }
        MovieScreening updatedMovieScreening = updateScreeningPort.update(id, movieScreening);

        return ScreeningMapperApplication.toResponseUpdate(updatedMovieScreening);
    }

    @Override
    public List<FindAllScreeningsByMovieIdResponse> findByMovieId(Long movieId){
        return findAllScreeningsByMovieIdPort.findByMovieId(movieId).stream()
                .map(ScreeningMapperApplication::toResponseFindAllByMovieId)
                .collect(Collectors.toList());
    }


    @Override
    public List<FindAllScreeningsByDateResponse> findAllByDate(LocalDate date){
        return findAllScreeningsByDatePort.findAllByDate(date).stream()
                .map(ScreeningMapperApplication::toResponseFindAllByDate)
                .collect(Collectors.toList());
    }
}
