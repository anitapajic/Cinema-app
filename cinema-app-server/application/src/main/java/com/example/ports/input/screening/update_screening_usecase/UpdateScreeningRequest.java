package com.example.ports.input.screening.update_screening_usecase;

import com.example.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateScreeningRequest {

    private LocalDateTime dateTime;

    private Long movieId;

    private Double price;

    private Integer seatCol;

    private Integer seatRow;
}
