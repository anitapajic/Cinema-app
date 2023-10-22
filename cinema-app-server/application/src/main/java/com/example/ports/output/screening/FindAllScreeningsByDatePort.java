package com.example.ports.output.screening;

import com.example.model.MovieScreening;

import java.time.LocalDate;
import java.util.List;

public interface FindAllScreeningsByDatePort {
    List<MovieScreening> findAllByDate(LocalDate date);
}
