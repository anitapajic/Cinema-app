package com.example.ports.input.movie.update_movie_usecase;

import com.example.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieRequest {

    private String name;

    private Integer duration;

    private Double rating;

    private String posterImage;

    private Set<Genre> genres;
}
