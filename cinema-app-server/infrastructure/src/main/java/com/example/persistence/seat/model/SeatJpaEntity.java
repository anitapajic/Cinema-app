package com.example.persistence.seat.model;

import com.example.model.enums.SeatStatus;
import com.example.persistence.screening.model.MovieScreeningJpaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SeatJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private SeatStatus seatStatus;
    @Column
    private Integer row;
    @Column
    private Integer col;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_screening_id")
    private MovieScreeningJpaEntity movieScreening;

    public SeatJpaEntity(SeatStatus seatStatus, Integer row, Integer col, MovieScreeningJpaEntity movieScreening){
    this.seatStatus = seatStatus;
    this.row = row;
    this.col = col;
    this.movieScreening = movieScreening;
    }
}
