package com.example.ports.input.screening.find_all_screenings_by_date_usecase;

import java.time.LocalDate;
import java.util.List;

public interface FindAllScreeningsByDateUsecase {
    List<FindAllScreeningsByDateResponse> findAllByDate(LocalDate date);
}
