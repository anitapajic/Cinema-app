package com.example.ports.input.genre.find_all_genres_usecase;

import com.example.model.Genre;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreResponse;

import java.util.List;

public interface FindAllGenresUsecase {
    List<FindAllGenresResponse> findAll();
}
