package com.example.ports.input.movie.save_movie_usecase;

import com.example.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveMovieRequest {
    @NotBlank
    private String name;
    @NotNull
    private Integer duration;

    private Double rating;

    private String posterImage;
    @NotEmpty
    private Set<Genre> genres;
}
