package com.example.ports.input.screening.find_all_screenings_by_date_usecase;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindAllScreeningsByDateResponse {
    private Long id;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private Long movieId;
    @NotNull
    private Double price;
    @NotNull
    private Integer seatCol;
    @NotNull
    private Integer seatRow;
}
