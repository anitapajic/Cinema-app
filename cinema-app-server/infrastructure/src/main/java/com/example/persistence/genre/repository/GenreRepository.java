package com.example.persistence.genre.repository;

import com.example.persistence.genre.model.GenreJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GenreRepository extends JpaRepository<GenreJpaEntity, Long> {

    Optional<GenreJpaEntity> findById(Long id);
    Optional<GenreJpaEntity> findByType(String type);

}
