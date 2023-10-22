package com.example.ports.input.genre.find_by_id_usecase;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindGenreByIdResponse {
    private Long id;
    @NotBlank
    private String type;
}
