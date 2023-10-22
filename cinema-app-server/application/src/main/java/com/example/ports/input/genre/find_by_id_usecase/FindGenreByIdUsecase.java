package com.example.ports.input.genre.find_by_id_usecase;

import com.example.ports.input.genre.save_genre_usecase.SaveGenreResponse;

public interface FindGenreByIdUsecase {
    FindGenreByIdResponse findById(Long id);
}
