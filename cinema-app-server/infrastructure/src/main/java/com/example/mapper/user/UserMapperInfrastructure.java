package com.example.mapper.user;

import com.example.model.User;
import com.example.persistence.user.model.UserJpaEntity;

public class UserMapperInfrastructure {
    public static User toDomain(UserJpaEntity userJpaEntity){
        return new User(userJpaEntity.getId(),
                userJpaEntity.getUsername(),
                userJpaEntity.getPassword(),
                userJpaEntity.getName(),
                userJpaEntity.getDateOfBirth(),
                userJpaEntity.getRole(),
                userJpaEntity.getVerified(),
                userJpaEntity.getBlocked());
    }

    public static UserJpaEntity toDataBase(User user){
        return new UserJpaEntity(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getVerified(),
                user.getBlocked());
    }
}
