package com.example.ports.output.movie;

import com.example.model.Movie;

public interface FindMovieByIdPort {
    Movie findById(Long id);
}
