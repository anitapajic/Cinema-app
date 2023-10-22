package com.example.ports.input.reservation.save_reservation_usecase;

import com.example.model.MovieScreening;
import com.example.model.Seat;
import com.example.model.enums.ReservationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveReservationResponse {
    private Long id;
    @NotBlank
    private String tag;
    @NotNull
    private LocalDateTime timestamp;
    @NotNull
    private ReservationStatus reservationStatus;
    @NotBlank
    private String userEmail;
    @NotNull
    private Integer numOfTickets;
    @NotNull
    private Double totalPrice;
    @NotEmpty
    private Set<SeatResponse> selectedSeats;
    @NotNull
    private Long movieScreeningId;
}
