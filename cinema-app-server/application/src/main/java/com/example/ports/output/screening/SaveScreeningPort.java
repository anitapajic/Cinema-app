package com.example.ports.output.screening;

import com.example.model.MovieScreening;

public interface SaveScreeningPort {
    MovieScreening save(MovieScreening movieScreening);
}
