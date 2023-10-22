package com.example.ports.output.genre;

import com.example.model.Genre;

public interface UpdateGenrePort {
    Genre update(Long id, Genre genre);
}
