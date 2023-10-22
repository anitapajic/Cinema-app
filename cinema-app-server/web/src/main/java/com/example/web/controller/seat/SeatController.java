package com.example.web.controller.seat;

import com.example.ports.input.seat.find_all_by_screening_id_usecase.FindAllSeatsByScreeningIdResponse;
import com.example.ports.input.seat.find_all_by_screening_id_usecase.FindAllSeatsByScreeningIdUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/seats")
public class SeatController {
    private final FindAllSeatsByScreeningIdUsecase findAllSeatsByScreeningIdUsecase;
    @GetMapping(value = "/screening/{screeningId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity getAllSeatsByScreeningId(@PathVariable Long screeningId) {
        List<FindAllSeatsByScreeningIdResponse> seats = findAllSeatsByScreeningIdUsecase.findAllByMovieScreeningId(screeningId);
        return new ResponseEntity(seats, HttpStatus.OK);
    }
}
