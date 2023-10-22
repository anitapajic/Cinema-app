package com.example.ports.input.screening.find_by_id_usecase;

import com.example.model.Movie;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindScreeningByIdResponse {
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
