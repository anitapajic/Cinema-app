package com.example.service;


import com.example.mapper.genre.GenreMapperApplication;
import com.example.ports.input.genre.delete_genre_usecase.DeleteGenreUsecase;
import com.example.ports.input.genre.find_all_genres_usecase.FindAllGenresResponse;
import com.example.ports.input.genre.find_all_genres_usecase.FindAllGenresUsecase;
import com.example.ports.input.genre.find_by_id_usecase.FindGenreByIdResponse;
import com.example.ports.input.genre.find_by_id_usecase.FindGenreByIdUsecase;
import com.example.ports.input.genre.find_by_type_usecase.FindGenreByTypeResponse;
import com.example.ports.input.genre.find_by_type_usecase.FindGenreByTypeUsecase;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreRequest;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreResponse;
import com.example.ports.input.genre.save_genre_usecase.SaveGenreUsecase;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreRequest;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreResponse;
import com.example.ports.input.genre.update_genre_usecase.UpdateGenreUsecase;
import com.example.ports.output.genre.*;
import com.example.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreService implements SaveGenreUsecase,
                                    FindAllGenresUsecase,
                                    FindGenreByIdUsecase,
                                    DeleteGenreUsecase,
                                    UpdateGenreUsecase,
                                    FindGenreByTypeUsecase {
    private final SaveGenrePort saveGenrePort;
    private final FindAllGenresPort findAllGenresPort;
    private final FindGenreByIdPort findGenreByIdPort;
    private final DeleteGenrePort deleteGenrePort;
    private final UpdateGenrePort updateGenrePort;
    private final FindGenreByTypePort findGenreByTypePort;

    @Override
    public SaveGenreResponse save(SaveGenreRequest saveGenreRequest) {
        Genre genre = GenreMapperApplication.toDomain(saveGenreRequest);
        Genre savedGenre = saveGenrePort.save(genre);
        return GenreMapperApplication.toResponse(savedGenre);
    }

    @Override
    public List<FindAllGenresResponse> findAll(){
        return findAllGenresPort.findAll().stream()
                .map(GenreMapperApplication::toResponseFindAll)
                .collect(Collectors.toList());
    }

    @Override
    public FindGenreByIdResponse findById(Long id){
        Genre genre = findGenreByIdPort.findById(id);
        return GenreMapperApplication.toResponseFindById(genre);
    }

    @Override
    public FindGenreByTypeResponse findByType(String type){
        Genre genre = findGenreByTypePort.findByType(type);
        return GenreMapperApplication.toResponseFindByType(genre);
    }

    @Override
    public void deleteById(Long id){
        deleteGenrePort.deleteById(id);
    }

    @Override
    public UpdateGenreResponse update(Long id, UpdateGenreRequest updateGenreRequest){
        Genre genre = GenreMapperApplication.toDomainUpdate(updateGenreRequest);
        Genre updatedGenre = updateGenrePort.update(id, genre);
        return GenreMapperApplication.toResponseUpdate(updatedGenre);
    }

}
