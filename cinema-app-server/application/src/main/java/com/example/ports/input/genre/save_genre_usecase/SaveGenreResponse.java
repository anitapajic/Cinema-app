package com.example.ports.input.genre.save_genre_usecase;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveGenreResponse {
    private Long id;
    @NotBlank
    private String type;
}
