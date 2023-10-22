package com.example.ports.input.seat.find_all_seats_usecase;

import com.example.model.MovieScreening;
import com.example.model.enums.SeatStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindAllSeatsResponse {
    private Long id;
    @NotNull
    private SeatStatus seatStatus;
    @NotNull
    private Integer row;
    @NotNull
    private Integer col;
    private MovieScreening movieScreening;
}
