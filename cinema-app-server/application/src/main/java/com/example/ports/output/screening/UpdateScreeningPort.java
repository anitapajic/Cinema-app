package com.example.ports.output.screening;

import com.example.model.MovieScreening;

public interface UpdateScreeningPort {
    MovieScreening update(Long id, MovieScreening movieScreening);
}
