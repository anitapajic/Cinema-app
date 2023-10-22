package com.example.ports.output.genre;

import com.example.model.Genre;

public interface FindGenreByIdPort {
    Genre findById(Long id);
}
