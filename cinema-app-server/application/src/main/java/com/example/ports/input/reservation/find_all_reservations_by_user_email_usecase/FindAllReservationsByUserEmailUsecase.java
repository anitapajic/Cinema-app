package com.example.ports.input.reservation.find_all_reservations_by_user_email_usecase;

import java.util.List;

public interface FindAllReservationsByUserEmailUsecase {
    List<FindAllReservationsByUserEmailResponse> findAllByUserEmail(String userEmail);
}
