package com.example.persistence.genre.adapters;

import com.example.exception.genre.GenreNotFoundException;
import com.example.mapper.genre.GenreMapperInfrastructure;
import com.example.model.Genre;
import com.example.persistence.genre.model.GenreJpaEntity;
import com.example.persistence.genre.repository.GenreRepository;
import com.example.ports.output.genre.UpdateGenrePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateGenreAdapter implements UpdateGenrePort {
    private final GenreRepository genreRepository;

    @Override
    public Genre update(Long id, Genre genre){
        GenreJpaEntity updatedGenre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
        updatedGenre.setType(genre.getType());
        return  GenreMapperInfrastructure.toDomain(genreRepository.save(updatedGenre));

    }
}
