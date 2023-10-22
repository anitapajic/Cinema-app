package com.example.persistence.movie.adapters;

import com.example.persistence.movie.repository.MovieRepository;
import com.example.ports.output.movie.DeleteMoviePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteMovieAdapter implements DeleteMoviePort {
    private final MovieRepository movieRepository;
    @Override
    public void deleteById(Long id){
        movieRepository.deleteById(id);
    }
}
