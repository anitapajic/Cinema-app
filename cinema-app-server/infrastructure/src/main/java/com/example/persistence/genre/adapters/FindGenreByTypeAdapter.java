package com.example.persistence.genre.adapters;

import com.example.exception.genre.GenreNotFoundException;
import com.example.mapper.genre.GenreMapperInfrastructure;
import com.example.model.Genre;
import com.example.persistence.genre.model.GenreJpaEntity;
import com.example.persistence.genre.repository.GenreRepository;
import com.example.ports.output.genre.FindGenreByTypePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FindGenreByTypeAdapter implements FindGenreByTypePort {
    private final GenreRepository genreRepository;

    @Override
    public Genre findByType(String type){
        GenreJpaEntity genreJpaEntity = genreRepository.findByType(type)
                .orElseThrow(() -> new GenreNotFoundException(type));
        return GenreMapperInfrastructure.toDomain(genreJpaEntity);
    }

}
