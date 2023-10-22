package com.example.ports.output.movie;

import com.example.model.Movie;

public interface SaveMoviePort {
    Movie save(Movie movie);
}
