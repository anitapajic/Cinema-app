package com.example.ports.input.seat.find_all_by_screening_id_usecase;



import java.util.List;

public interface FindAllSeatsByScreeningIdUsecase {
    List<FindAllSeatsByScreeningIdResponse> findAllByMovieScreeningId(Long id);
}
