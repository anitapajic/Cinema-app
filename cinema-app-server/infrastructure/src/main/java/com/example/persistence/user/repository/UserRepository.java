package com.example.persistence.user.repository;

import com.example.model.enums.Role;
import com.example.persistence.user.model.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByUsername(String username);
    List<UserJpaEntity> findByRole(Role role);
}
