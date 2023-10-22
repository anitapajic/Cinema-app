package com.example.ports.output.seat;

import com.example.model.Seat;

import java.util.List;

public interface FindAllSeatsByMovieScreeningIdPort {
    List<Seat> findAllByMovieScreeningId(Long id);
}
