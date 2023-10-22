package com.example.ports.input.movie.update_movie_usecase;

public interface UpdateMovieUsecase {
    UpdateMovieResponse update(Long id, UpdateMovieRequest updateMovieRequest);
}
