package com.example.persistence.seat.repository;

import com.example.persistence.seat.model.SeatJpaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatJpaEntity, Long> {

    @Transactional
    List<SeatJpaEntity> findByMovieScreeningIdOrderByIdAsc(Long movieScreeningId);
}
