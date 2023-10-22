package com.example.ports.input.genre.find_by_type_usecase;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindGenreByTypeResponse {
    private Long id;
    @NotBlank
    private String type;
}
