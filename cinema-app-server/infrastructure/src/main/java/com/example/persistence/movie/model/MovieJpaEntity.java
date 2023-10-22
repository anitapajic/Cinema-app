package com.example.persistence.movie.model;

import com.example.persistence.genre.model.GenreJpaEntity;
import com.example.persistence.screening.model.MovieScreeningJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private Integer duration;
    @Column
    private Double rating;
    @Lob
    @Column
    private String posterImage;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreJpaEntity> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieScreeningJpaEntity> screenings;

    public MovieJpaEntity(Long id, String name, Integer duration, Double rating, String posterImage, Set<GenreJpaEntity> genres){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.rating = rating;
        this.posterImage = posterImage;
        this.genres = genres;
    }
}
