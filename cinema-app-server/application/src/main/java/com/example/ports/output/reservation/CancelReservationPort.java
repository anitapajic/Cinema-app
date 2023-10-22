package com.example.ports.output.reservation;

import com.example.model.Reservation;

public interface CancelReservationPort {
    Reservation cancel(Long id);
}
