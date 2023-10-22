package com.example.model;

import com.example.model.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private Long id;
    private SeatStatus seatStatus;
    private Integer row;
    private Integer col;
    private MovieScreening movieScreening;

    public Seat(SeatStatus seatStatus, Integer row, Integer col, MovieScreening movieScreening){
        this.seatStatus = seatStatus;
        this.row = row;
        this.col = col;
        this.movieScreening = movieScreening;
    }
}
