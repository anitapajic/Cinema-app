package com.example.exception.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ReservationNotSentOnMailException extends RuntimeException{
    public ReservationNotSentOnMailException(Long id) {
        super(String.format("Reservation with id: %s is not sent to user's mail!", id));
    }
}
