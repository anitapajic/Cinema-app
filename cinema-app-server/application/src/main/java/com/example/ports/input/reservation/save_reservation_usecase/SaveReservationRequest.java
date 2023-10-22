package com.example.ports.input.reservation.save_reservation_usecase;

import com.example.model.Seat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveReservationRequest {
    @NotBlank
    private String userEmail;
    @NotNull
    private Integer numOfTickets;
    @NotNull
    private Double totalPrice;
    @NotEmpty
    private Set<Seat> selectedSeats;
    @NotNull
    private Long screeningId;
}
