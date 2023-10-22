package com.example.ports.input.screening.find_all_by_movie_id_usecase;

import java.util.List;

public interface FindAllScreeningsByMovieIdUsecase {
    List<FindAllScreeningsByMovieIdResponse> findByMovieId(Long movieId);
}
