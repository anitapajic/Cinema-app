package com.example.configuration;

import com.example.persistence.reservation.adapters.CancelReservationAdapter;
import com.example.persistence.reservation.adapters.FindAllReservationsByUserEmailAdapter;
import com.example.persistence.reservation.adapters.SaveReservationAdapter;
import com.example.persistence.reservation.adapters.SendReservationMailAdapter;
import com.example.persistence.reservation.repository.ReservationRepository;
import com.example.persistence.seat.repository.SeatRepository;
import com.example.ports.input.reservation.cancel_reservation_usecase.CancelReservationUsecase;
import com.example.ports.input.reservation.find_all_reservations_by_user_email_usecase.FindAllReservationsByUserEmailUsecase;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationUsecase;
import com.example.ports.output.reservation.CancelReservationPort;
import com.example.ports.output.reservation.FindAllReservationsByUserEmailPort;
import com.example.ports.output.reservation.SaveReservationPort;
import com.example.ports.output.reservation.SendReservationMailPort;
import com.example.ports.output.screening.FindScreeningByIdPort;
import com.example.service.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class ReservationConfiguration {

    //======================================================================================
    // PORTS
    //======================================================================================

    @Bean
    SendReservationMailPort sendReservationMailPort(JavaMailSender javaMailSender){
        return new SendReservationMailAdapter(javaMailSender);
    }
    @Bean
    SaveReservationPort saveReservationPort(ReservationRepository reservationRepository, SeatRepository seatRepository){
        return new SaveReservationAdapter(reservationRepository, seatRepository);
    }
    @Bean
    FindAllReservationsByUserEmailPort findAllReservationsByUserEmailPort(ReservationRepository reservationRepository){
        return new FindAllReservationsByUserEmailAdapter(reservationRepository);
    }
    @Bean
    CancelReservationPort cancelReservationPort(ReservationRepository reservationRepository, SeatRepository seatRepository){
        return new CancelReservationAdapter(reservationRepository, seatRepository);
    }


    //======================================================================================
    // USE CASES
    //======================================================================================

    @Bean
    SaveReservationUsecase saveReservationUsecase(SaveReservationPort saveReservationPort, FindScreeningByIdPort findScreeningByIdPort, SendReservationMailPort sendReservationMailPort, FindAllReservationsByUserEmailPort findAllReservationsByUserEmailPort, CancelReservationPort cancelReservationPort){
        return new ReservationService(saveReservationPort, findScreeningByIdPort, sendReservationMailPort, findAllReservationsByUserEmailPort, cancelReservationPort);
    }
    @Bean
    FindAllReservationsByUserEmailUsecase findAllReservationsByUserEmailUsecase(SaveReservationPort saveReservationPort, FindScreeningByIdPort findScreeningByIdPort, SendReservationMailPort sendReservationMailPort, FindAllReservationsByUserEmailPort findAllReservationsByUserEmailPort, CancelReservationPort cancelReservationPort){
        return new ReservationService(saveReservationPort, findScreeningByIdPort, sendReservationMailPort, findAllReservationsByUserEmailPort, cancelReservationPort);
    }
    @Bean
    CancelReservationUsecase cancelReservationUsecase(SaveReservationPort saveReservationPort, FindScreeningByIdPort findScreeningByIdPort, SendReservationMailPort sendReservationMailPort, FindAllReservationsByUserEmailPort findAllReservationsByUserEmailPort, CancelReservationPort cancelReservationPort){
        return new ReservationService(saveReservationPort, findScreeningByIdPort, sendReservationMailPort, findAllReservationsByUserEmailPort, cancelReservationPort);
    }
}
