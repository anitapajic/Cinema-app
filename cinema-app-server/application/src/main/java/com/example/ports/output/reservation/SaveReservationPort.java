package com.example.ports.output.reservation;

import com.example.model.Reservation;

public interface SaveReservationPort {
    Reservation save(Reservation reservation);
}
