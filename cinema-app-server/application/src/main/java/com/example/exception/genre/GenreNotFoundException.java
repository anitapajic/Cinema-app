package com.example.exception.genre;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super(String.format("Genre with id: %s not found", id));
    }

    public GenreNotFoundException(String type){
        super(String.format("Genre with type: %s not found", type));
    }
}
