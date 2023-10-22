package com.example.ports.output.reservation;

import com.example.model.Reservation;

import java.util.List;

public interface FindAllReservationsByUserEmailPort {
    List<Reservation> findAllByUserEmail(String userEmail);
}
