package com.example.persistence.reservation.model;

import com.example.model.MovieScreening;
import com.example.model.enums.ReservationStatus;
import com.example.persistence.screening.model.MovieScreeningJpaEntity;
import com.example.persistence.seat.model.SeatJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tag;

    @Column
    private LocalDateTime timestamp;
    @Column
    private String userEmail;
    @Column
    private Integer numOfTickets;
    @Column
    private Double totalPrice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reservation_seat",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private Set<SeatJpaEntity> selectedSeats;
    @Column
    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_screening_id")
    private MovieScreeningJpaEntity movieScreening;

    public ReservationJpaEntity(LocalDateTime timestamp, String userEmail, Integer numOfTickets, Double totalPrice, Set<SeatJpaEntity> selectedSeats, ReservationStatus reservationStatus, MovieScreeningJpaEntity movieScreening) {
        this.timestamp = timestamp;
        this.userEmail = userEmail;
        this.numOfTickets = numOfTickets;
        this.totalPrice = totalPrice;
        this.selectedSeats = selectedSeats;
        this.reservationStatus = reservationStatus;
        this.movieScreening = movieScreening;
    }
}
