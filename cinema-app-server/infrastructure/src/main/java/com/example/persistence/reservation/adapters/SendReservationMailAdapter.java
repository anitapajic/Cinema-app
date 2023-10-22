package com.example.persistence.reservation.adapters;

import com.example.model.Reservation;
import com.example.model.Seat;
import com.example.ports.output.reservation.SendReservationMailPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SendReservationMailAdapter implements SendReservationMailPort {
    private final JavaMailSender mailSender;

    public void sendMail(Reservation reservation) throws MessagingException, UnsupportedEncodingException {
        String subject = "Reservation details";
        String senderName = "Cinema App";

        String mailContent = "<p>Dear, user </p>";
        mailContent +="<h3>Below are your reservation details:</h3>";
        mailContent +="<p>Reservation for movie <b>" + reservation.getMovieScreening().getMovie().getName() + "</b> on " + reservation.getMovieScreening().getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")) + " at <b>" + reservation.getMovieScreening().getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "h</b></p>";
        mailContent += "<p>Reservation ID: " + reservation.getTag() + "</p>";
        mailContent += "<p>Reserved on " + reservation.getTimestamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")) + " at " + reservation.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm")) + "h.</p>";
        mailContent += "<p>Number of reserved tickets: " + reservation.getNumOfTickets() + "</p>";
        mailContent += "<p>Total price: " + reservation.getTotalPrice() + " RSD</p>";
        String selectedSeats = reservation.getSelectedSeats().stream()
                .map(seat -> "Row: " + seat.getRow() + ", Number: " + seat.getCol())
                .collect(Collectors.joining("; "));

        mailContent += "<p>Selected seats: " + selectedSeats + "</p>";
        mailContent +="<p>Thank you for your reservation!<br>Cinema App Team</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("UberAppTim19@gmail.com", senderName);
        helper.setTo("anitaapajic@gmail.com");
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }
}
