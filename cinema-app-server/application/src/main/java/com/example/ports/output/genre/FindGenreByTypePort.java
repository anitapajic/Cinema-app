package com.example.ports.output.genre;

import com.example.model.Genre;

public interface FindGenreByTypePort {
    Genre findByType(String id);
}
