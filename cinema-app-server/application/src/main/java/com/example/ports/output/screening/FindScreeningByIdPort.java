package com.example.ports.output.screening;

import com.example.model.MovieScreening;

public interface FindScreeningByIdPort {
    MovieScreening findById(Long id);
}
