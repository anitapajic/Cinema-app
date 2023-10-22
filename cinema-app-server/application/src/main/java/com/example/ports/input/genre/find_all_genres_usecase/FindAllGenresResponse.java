package com.example.ports.input.genre.find_all_genres_usecase;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindAllGenresResponse {
    private Long id;
    @NotBlank
    private String type;
}
