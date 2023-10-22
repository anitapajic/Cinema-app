package com.example.persistence.user.adapters;

import com.example.mapper.user.UserMapperInfrastructure;
import com.example.model.User;
import com.example.model.enums.Role;
import com.example.persistence.user.repository.UserRepository;
import com.example.ports.output.user.FindAllConsumersPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllConsumersAdapter implements FindAllConsumersPort {
    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers(){
        return userRepository.findByRole(Role.CONSUMER).stream()
                .map(UserMapperInfrastructure::toDomain)
                .collect(Collectors.toList());
    }
}
