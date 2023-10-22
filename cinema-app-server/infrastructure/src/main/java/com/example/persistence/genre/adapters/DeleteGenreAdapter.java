package com.example.persistence.genre.adapters;

import com.example.persistence.genre.repository.GenreRepository;
import com.example.ports.output.genre.DeleteGenrePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteGenreAdapter implements DeleteGenrePort {
    private final GenreRepository genreRepository;

    @Override
    public void deleteById(Long id){
       genreRepository.deleteById(id);
    }
}
