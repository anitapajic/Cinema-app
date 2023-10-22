package com.example.ports.input.genre.update_genre_usecase;

import com.example.ports.input.genre.save_genre_usecase.SaveGenreRequest;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreResponse;

public interface UpdateGenreUsecase {
    UpdateGenreResponse update(Long id, UpdateGenreRequest updateGenreRequest);
}
