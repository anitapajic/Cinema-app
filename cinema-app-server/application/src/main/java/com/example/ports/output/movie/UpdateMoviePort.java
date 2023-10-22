package com.example.ports.output.movie;

import com.example.model.Movie;

public interface UpdateMoviePort {
    Movie update(Long id, Movie movie);
}
