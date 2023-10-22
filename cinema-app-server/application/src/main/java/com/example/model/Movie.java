package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;

    private String name;

    private Integer duration;

    private Double rating;

    private String posterImage;

    private Set<Genre> genres = new HashSet<>();

    public Movie(String name, Integer duration, Double rating, String posterImage, Set<Genre> genres){
        this.name = name;
        this.duration = duration;
        this.rating = rating;
        this.posterImage = posterImage;
        this.genres = genres;
    }


}
