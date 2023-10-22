package com.example.ports.output.reservation;

import com.example.model.Reservation;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface SendReservationMailPort {
    void sendMail(Reservation reservation) throws MessagingException, UnsupportedEncodingException;
}
