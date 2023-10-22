package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieScreening {
    private Long id;
    private LocalDateTime dateTime;
    private Movie movie;
    private Double price;
    private Integer seatCol;
    private Integer seatRow;


    public MovieScreening(LocalDateTime dateTime, Movie movie, Double price, Integer seatCol, Integer seatRow){
        this.dateTime = dateTime;
        this.movie = movie;
        this.price = price;
        this.seatCol = seatCol;
        this.seatRow = seatRow;
    }

}
