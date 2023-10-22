package com.example.persistence.screening.adapters;

import com.example.persistence.screening.repository.MovieScreeningRepository;
import com.example.ports.output.screening.DeleteScreeningPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteMovieScreeningAdapter implements DeleteScreeningPort {
    private final MovieScreeningRepository movieScreeningRepository;
    @Override
    public void deleteById(Long id){
        movieScreeningRepository.deleteById(id);
    }
}
