package com.example.mapper.reservation;

import com.example.model.MovieScreening;
import com.example.model.Reservation;
import com.example.model.Seat;
import com.example.ports.input.reservation.cancel_reservation_usecase.CancelReservationResponse;
import com.example.ports.input.reservation.find_all_reservations_by_user_email_usecase.FindAllReservationsByUserEmailResponse;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationRequest;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationResponse;
import com.example.ports.input.reservation.save_reservation_usecase.SeatResponse;

import java.util.stream.Collectors;

public class ReservationMapperApplication {
    public static Reservation toDomain(SaveReservationRequest saveReservationRequest, MovieScreening movieScreening){
        return new Reservation(saveReservationRequest.getUserEmail(),
                               saveReservationRequest.getNumOfTickets(),
                               saveReservationRequest.getTotalPrice(),
                               saveReservationRequest.getSelectedSeats(),
                               movieScreening);

    }
    public static SaveReservationResponse toResponse(Reservation reservation){
        return new SaveReservationResponse(reservation.getId(),
                reservation.getTag(),
                reservation.getTimestamp(),
                reservation.getReservationStatus(),
                reservation.getUserEmail(),
                reservation.getNumOfTickets(),
                reservation.getTotalPrice(),
                reservation.getSelectedSeats().stream()
                        .map(ReservationMapperApplication::seatToResponse)
                        .collect(Collectors.toSet()),
                reservation.getMovieScreening().getId());
    }

    public static SeatResponse seatToResponse(Seat seat){
        return new SeatResponse(seat.getId(),
                                seat.getSeatStatus(),
                                seat.getRow(),
                                seat.getCol(),
                                seat.getMovieScreening().getId());
    }

    public static FindAllReservationsByUserEmailResponse toResponseFindAllByUserId(Reservation reservation){
        return new FindAllReservationsByUserEmailResponse(reservation.getId(),
                reservation.getTag(),
        reservation.getTimestamp(),
                reservation.getReservationStatus(),
                reservation.getUserEmail(),
                reservation.getNumOfTickets(),
                reservation.getTotalPrice(),
                reservation.getSelectedSeats().stream()
                        .map(ReservationMapperApplication::seatToResponse)
                        .collect(Collectors.toSet()),
                reservation.getMovieScreening().getId()
                );
    }

    public static CancelReservationResponse cancelReservationResponse(Reservation reservation){
        return new CancelReservationResponse(reservation.getId(),
                reservation.getTag(),
                reservation.getTimestamp(),
                reservation.getReservationStatus(),
                reservation.getUserEmail(),
                reservation.getNumOfTickets(),
                reservation.getTotalPrice(),
                reservation.getSelectedSeats().stream()
                        .map(ReservationMapperApplication::seatToResponse)
                        .collect(Collectors.toSet()),
                reservation.getMovieScreening().getId()
        );
    }
}
