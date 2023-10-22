package com.example.web.controller.reservation;

import com.example.ports.input.reservation.cancel_reservation_usecase.CancelReservationResponse;
import com.example.ports.input.reservation.cancel_reservation_usecase.CancelReservationUsecase;
import com.example.ports.input.reservation.find_all_reservations_by_user_email_usecase.FindAllReservationsByUserEmailResponse;
import com.example.ports.input.reservation.find_all_reservations_by_user_email_usecase.FindAllReservationsByUserEmailUsecase;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationRequest;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationResponse;
import com.example.ports.input.reservation.save_reservation_usecase.SaveReservationUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final SaveReservationUsecase saveReservationUsecase;
    private final FindAllReservationsByUserEmailUsecase findAllReservationsByUserEmailUsecase;
    private final CancelReservationUsecase cancelReservationUsecase;

    @PostMapping
    public ResponseEntity createReservation(@Valid @RequestBody SaveReservationRequest saveReservationRequest){
        SaveReservationResponse saveReservationResponse = saveReservationUsecase.save(saveReservationRequest);
        System.out.println(saveReservationResponse.getSelectedSeats());
        return new ResponseEntity<>(saveReservationResponse, HttpStatus.CREATED);
    }
    @GetMapping(value = "/{userEmail}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity getReservationsByUserEmail(@PathVariable String userEmail){
        List<FindAllReservationsByUserEmailResponse> findAllReservationsByUserEmailResponse = findAllReservationsByUserEmailUsecase.findAllByUserEmail(userEmail);
        return new ResponseEntity<>(findAllReservationsByUserEmailResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/cancel/{id}")
    @PreAuthorize("hasAnyAuthority('CONSUMER')")
    public ResponseEntity cancelReservation(@PathVariable Long id){
        CancelReservationResponse cancelReservationResponse = cancelReservationUsecase.cancel(id);
        return new ResponseEntity<>(cancelReservationResponse, HttpStatus.OK);
    }

}
