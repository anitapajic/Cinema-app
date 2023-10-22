package com.example.exception.screening;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScreeningNotFoundException extends RuntimeException {
    public ScreeningNotFoundException(Long id) {
        super(String.format("Movie screening with id: %s not found", id));
    }
}
