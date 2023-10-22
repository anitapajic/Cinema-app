package com.example.persistence.movie.repository;

import com.example.persistence.movie.model.MovieJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieJpaEntity, Long> {

    Optional<MovieJpaEntity> findById(Long id);
}
