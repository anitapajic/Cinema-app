package com.example.ports.output.movie;

import com.example.model.Movie;

import java.util.List;

public interface FindAllMoviesPort {
    List<Movie> findAll();
}
