package com.example.persistence.screening.model;

import com.example.persistence.movie.model.MovieJpaEntity;
import com.example.persistence.reservation.model.ReservationJpaEntity;
import com.example.persistence.seat.model.SeatJpaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="movie_screening")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieScreeningJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private MovieJpaEntity movie;

    @Column
    private Double price;

    @Column
    private Integer seatCol;

    @Column
    private Integer seatRow;

    @OneToMany(mappedBy = "movieScreening", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SeatJpaEntity> seats;


    @OneToMany(mappedBy = "movieScreening", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReservationJpaEntity> reservations;

    public MovieScreeningJpaEntity(Long id, LocalDateTime dateTime, MovieJpaEntity movie, Double price, Integer seatCol, Integer seatRow){
        this.id = id;
        this.dateTime = dateTime;
        this.movie = movie;
        this.price = price;
        this.seatCol = seatCol;
        this.seatRow = seatRow;

    }
}
