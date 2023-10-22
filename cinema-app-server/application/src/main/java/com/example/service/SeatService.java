package com.example.service;

import com.example.mapper.seat.SeatMapperApplication;
import com.example.ports.input.seat.find_all_by_screening_id_usecase.FindAllSeatsByScreeningIdResponse;
import com.example.ports.input.seat.find_all_by_screening_id_usecase.FindAllSeatsByScreeningIdUsecase;
import com.example.ports.input.seat.find_all_seats_usecase.FindAllSeatsResponse;
import com.example.ports.input.seat.find_all_seats_usecase.FindAllSeatsUsecase;
import com.example.ports.output.seat.FindAllSeatsByMovieScreeningIdPort;
import com.example.ports.output.seat.FindAllSeatsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SeatService implements FindAllSeatsUsecase, FindAllSeatsByScreeningIdUsecase {
    private final FindAllSeatsPort findAllSeatsPort;
    private final FindAllSeatsByMovieScreeningIdPort findAllSeatsByMovieScreeningIdPort;

    @Override
    public List<FindAllSeatsResponse> findAll(){
        return findAllSeatsPort.findAll().stream()
                .map(SeatMapperApplication::toResponseFindAll)
                .collect(Collectors.toList());
    }
    @Override
    public List<FindAllSeatsByScreeningIdResponse> findAllByMovieScreeningId(Long id){
        return findAllSeatsByMovieScreeningIdPort.findAllByMovieScreeningId(id).stream()
                .map(SeatMapperApplication::toResponseFindAllByScreeningId)
                .collect(Collectors.toList());
    }
}
