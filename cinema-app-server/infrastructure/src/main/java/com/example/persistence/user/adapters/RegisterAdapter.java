package com.example.persistence.user.adapters;

import com.example.mapper.user.UserMapperInfrastructure;
import com.example.model.User;
import com.example.model.enums.Role;
import com.example.persistence.user.model.UserJpaEntity;
import com.example.persistence.user.repository.UserRepository;
import com.example.ports.output.user.RegisterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterAdapter implements RegisterPort{
    private final UserRepository userRepository;

    @Override
    public User register(User user){
        UserJpaEntity userJpaEntity = UserMapperInfrastructure.toDataBase(user);
        userJpaEntity.setBlocked(false);
        userJpaEntity.setVerified(false);
        userJpaEntity.setRole(Role.CONSUMER);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userJpaEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        UserJpaEntity savedUser = userRepository.save(userJpaEntity);

        return UserMapperInfrastructure.toDomain(savedUser);
    }
}
