package com.example.persistence.user.adapters;

import com.example.exception.movie.MovieNotFoundException;
import com.example.exception.user.UserNotFoundException;
import com.example.mapper.user.UserMapperInfrastructure;
import com.example.model.User;
import com.example.persistence.user.model.UserJpaEntity;
import com.example.persistence.user.repository.UserRepository;
import com.example.ports.output.user.VerifyAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyAccountAdapter implements VerifyAccountPort {
    private final UserRepository userRepository;

    @Override
    public User verify(Long id){
        UserJpaEntity userJpaEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userJpaEntity.setVerified(true);
        userRepository.save(userJpaEntity);
        return UserMapperInfrastructure.toDomain(userJpaEntity);
    }
}
