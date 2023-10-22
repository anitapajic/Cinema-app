package com.example.mapper.genre;

import com.example.ports.input.genre.find_all_genres_usecase.FindAllGenresResponse;
import com.example.ports.input.genre.find_by_id_usecase.FindGenreByIdResponse;
import com.example.ports.input.genre.find_by_type_usecase.FindGenreByTypeResponse;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreRequest;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreResponse;
import com.example.model.Genre;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreRequest;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreResponse;

public class GenreMapperApplication {
    public static Genre toDomain(SaveGenreRequest saveGenreRequest) {
        return new Genre(saveGenreRequest.getType());
    }

    public static SaveGenreResponse toResponse(Genre genre) {
        return new SaveGenreResponse(genre.getId(), genre.getType());
    }

    public static Genre toDomainUpdate(UpdateGenreRequest updateGenreRequest) {
        return new Genre(updateGenreRequest.getType());
    }

    public static UpdateGenreResponse toResponseUpdate(Genre genre) {
        return new UpdateGenreResponse(genre.getId(), genre.getType());
    }

    public static FindAllGenresResponse toResponseFindAll(Genre genre) {
        return new FindAllGenresResponse(genre.getId(), genre.getType());
    }

    public static FindGenreByIdResponse toResponseFindById(Genre genre) {
        return new FindGenreByIdResponse(genre.getId(), genre.getType());
    }

    public static FindGenreByTypeResponse toResponseFindByType(Genre genre) {
        return new FindGenreByTypeResponse(genre.getId(), genre.getType());
    }


}
