package com.example.persistence.reservation.repository;

import com.example.persistence.reservation.model.ReservationJpaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationJpaEntity, Long> {
    @Transactional
    List<ReservationJpaEntity> findAllByUserEmail(String userEmail);
}
