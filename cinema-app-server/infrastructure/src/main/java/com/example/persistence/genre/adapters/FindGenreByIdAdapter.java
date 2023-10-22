package com.example.persistence.genre.adapters;

import com.example.exception.genre.GenreNotFoundException;
import com.example.mapper.genre.GenreMapperInfrastructure;
import com.example.model.Genre;
import com.example.persistence.genre.model.GenreJpaEntity;
import com.example.persistence.genre.repository.GenreRepository;
import com.example.ports.output.genre.FindGenreByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FindGenreByIdAdapter implements FindGenreByIdPort {
    private final GenreRepository genreRepository;

    @Override
    public Genre findById(Long id){
        GenreJpaEntity genreJpaEntity = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
        return GenreMapperInfrastructure.toDomain(genreJpaEntity);
    }
}
