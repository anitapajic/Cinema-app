package com.example.ports.input.reservation.find_all_reservations_by_user_email_usecase;

import com.example.model.enums.ReservationStatus;
import com.example.ports.input.reservation.save_reservation_usecase.SeatResponse;
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
public class FindAllReservationsByUserEmailResponse {
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
