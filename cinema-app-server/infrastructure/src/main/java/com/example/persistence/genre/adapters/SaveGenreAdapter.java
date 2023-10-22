package com.example.persistence.genre.adapters;

import com.example.mapper.genre.GenreMapperInfrastructure;
import com.example.model.Genre;
import com.example.persistence.genre.model.GenreJpaEntity;
import com.example.persistence.genre.repository.GenreRepository;
import com.example.ports.output.genre.SaveGenrePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveGenreAdapter implements SaveGenrePort {
    private final GenreRepository genreRepository;

    @Override
    public Genre save(Genre genre) {
        GenreJpaEntity genreJpaEntity = GenreMapperInfrastructure.toDataBase(genre);
        GenreJpaEntity savedGenre = genreRepository.save(genreJpaEntity);
        return GenreMapperInfrastructure.toDomain(savedGenre);
    }
}
