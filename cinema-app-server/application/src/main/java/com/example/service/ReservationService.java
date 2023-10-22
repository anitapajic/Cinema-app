package com.example.service;

import com.example.exception.reservation.ReservationNotSentOnMailException;
import com.example.mapper.reservation.ReservationMapperApplication;
import com.example.model.MovieScreening;
import com.example.model.Reservation;
import com.example.ports.input.reservation.cancel_reservation_usecase.CancelReservationResponse;
import com.example.ports.input.reservation.cancel_reservation_usecase.CancelReservationUsecase;
import com.example.ports.input.reservation.find_all_reservations_by_user_email_usecase.FindAllReservationsByUserEmailResponse;
import com.example.ports.input.reservation.find_all_reservations_by_user_email_usecase.FindAllReservationsByUserEmailUsecase;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationRequest;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationResponse;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationUsecase;
import com.example.ports.output.reservation.CancelReservationPort;
import com.example.ports.output.reservation.FindAllReservationsByUserEmailPort;
import com.example.ports.output.reservation.SaveReservationPort;
import com.example.ports.output.reservation.SendReservationMailPort;
import com.example.ports.output.screening.FindScreeningByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService implements SaveReservationUsecase,
                                            FindAllReservationsByUserEmailUsecase,
                                            CancelReservationUsecase {
    private final SaveReservationPort saveReservationPort;
    private final FindScreeningByIdPort findScreeningByIdPort;
    private final SendReservationMailPort sendReservationMailPort;
    private final FindAllReservationsByUserEmailPort findAllReservationsByUserEmailPort;
    private  final CancelReservationPort cancelReservationPort;

    @Override
    public SaveReservationResponse save(SaveReservationRequest saveReservationRequest){
        MovieScreening movieScreening = findScreeningByIdPort.findById(saveReservationRequest.getScreeningId());
        Reservation reservation = ReservationMapperApplication.toDomain(saveReservationRequest, movieScreening);
        Reservation savedReservation = saveReservationPort.save(reservation);
        try{
            sendReservationMailPort.sendMail(savedReservation);
        }catch(Exception e){
            throw new RuntimeException(new ReservationNotSentOnMailException(savedReservation.getId()));
        }
        return ReservationMapperApplication.toResponse(savedReservation);
    }

    @Override
    public List<FindAllReservationsByUserEmailResponse> findAllByUserEmail(String userEmail){
        return findAllReservationsByUserEmailPort.findAllByUserEmail(userEmail).stream()
                .map(ReservationMapperApplication::toResponseFindAllByUserId)
                .collect(Collectors.toList());
    }

    @Override
    public CancelReservationResponse cancel(Long id){
        Reservation reservation = cancelReservationPort.cancel(id);
        return ReservationMapperApplication.cancelReservationResponse(reservation);
    }

}
