package com.example.ports.output.genre;

import com.example.model.Genre;
import org.springframework.stereotype.Component;

public interface SaveGenrePort {
    Genre save(Genre genre);
}
