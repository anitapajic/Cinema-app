package com.example.persistence.screening.repository;

import com.example.model.MovieScreening;
import com.example.persistence.screening.model.MovieScreeningJpaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovieScreeningRepository extends JpaRepository<MovieScreeningJpaEntity, Long> {
    @Transactional
    List<MovieScreeningJpaEntity> findByMovieId(Long movieId);
    @Transactional
    @Query("SELECT ms FROM MovieScreeningJpaEntity ms WHERE DATE(ms.dateTime) = :date")
    List<MovieScreeningJpaEntity> findAllByDate(@Param("date") LocalDate date);
}
