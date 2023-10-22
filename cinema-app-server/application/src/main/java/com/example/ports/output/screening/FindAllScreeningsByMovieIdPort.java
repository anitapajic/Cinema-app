package com.example.ports.output.screening;

import com.example.model.MovieScreening;

import java.util.List;

public interface FindAllScreeningsByMovieIdPort {
    List<MovieScreening> findByMovieId(Long movieId);
}
