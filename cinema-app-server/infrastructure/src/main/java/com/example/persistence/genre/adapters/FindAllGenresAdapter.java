package com.example.persistence.genre.adapters;

import com.example.mapper.genre.GenreMapperApplication;
import com.example.mapper.genre.GenreMapperInfrastructure;
import com.example.model.Genre;
import com.example.persistence.genre.repository.GenreRepository;
import com.example.ports.output.genre.FindAllGenresPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FindAllGenresAdapter implements FindAllGenresPort {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll().stream()
                .map(GenreMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }
}
