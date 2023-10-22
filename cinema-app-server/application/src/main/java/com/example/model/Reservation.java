package com.example.model;

import com.example.model.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Long id;
    private String tag;
    private LocalDateTime timestamp;
    private String userEmail;
    private Integer numOfTickets;
    private Double totalPrice;
    private Set<Seat> selectedSeats;
    private ReservationStatus reservationStatus;
    private MovieScreening movieScreening;

    public Reservation(LocalDateTime timestamp, String userEmail, Integer numOfTickets, Double totalPrice, Set<Seat> selectedSeats, ReservationStatus reservationStatus, MovieScreening movieScreening) {
        this.timestamp = timestamp;
        this.userEmail = userEmail;
        this.numOfTickets = numOfTickets;
        this.totalPrice = totalPrice;
        this.selectedSeats = selectedSeats;
        this.reservationStatus = reservationStatus;
        this.movieScreening = movieScreening;
    }
    public Reservation(String userEmail, Integer numOfTickets, Double totalPrice, Set<Seat> selectedSeats, MovieScreening movieScreening){
        this.userEmail = userEmail;
        this.numOfTickets = numOfTickets;
        this.totalPrice = totalPrice;
        this.selectedSeats = selectedSeats;
        this.movieScreening = movieScreening;
    }
}
