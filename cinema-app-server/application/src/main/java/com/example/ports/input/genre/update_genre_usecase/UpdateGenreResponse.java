package com.example.ports.input.genre.update_genre_usecase;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGenreResponse {
    private Long id;
    @NotBlank
    private String type;
}
