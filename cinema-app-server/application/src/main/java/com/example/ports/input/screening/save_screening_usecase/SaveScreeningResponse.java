package com.example.ports.input.screening.save_screening_usecase;

import com.example.model.Movie;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveScreeningResponse {
    private Long id;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private Movie movie;
    @NotNull
    private Double price;
    @NotNull
    private Integer seatCol;
    @NotNull
    private Integer seatRow;
}
