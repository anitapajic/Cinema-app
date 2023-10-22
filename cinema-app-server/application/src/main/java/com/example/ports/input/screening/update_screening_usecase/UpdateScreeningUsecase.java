package com.example.ports.input.screening.update_screening_usecase;

public interface UpdateScreeningUsecase {
    UpdateScreeningResponse update(Long id, UpdateScreeningRequest updateScreeningRequest);
}
