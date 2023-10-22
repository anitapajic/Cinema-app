package com.example.persistence.user.adapters;

import com.example.exception.user.UserNotFoundException;
import com.example.mapper.user.UserMapperInfrastructure;
import com.example.model.User;
import com.example.persistence.user.model.UserJpaEntity;
import com.example.persistence.user.repository.UserRepository;
import com.example.ports.output.user.FindByUsernamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindByUsernameAdapter implements FindByUsernamePort {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username){
        UserJpaEntity userJpaEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return UserMapperInfrastructure.toDomain(userJpaEntity);
    }
}
