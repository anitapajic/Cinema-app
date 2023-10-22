package com.example.mapper.user;

import com.example.model.User;
import com.example.ports.input.user.block_consumer_usecase.BlockConsumerResponse;
import com.example.ports.input.user.find_all_consumers_usecase.FindAllConsumersResponse;
import com.example.ports.input.user.find_by_username_usecase.FindByUsernameResponse;
import com.example.ports.input.user.login_usecase.LoginResponse;
import com.example.ports.input.user.register_usecase.RegisterRequest;
import com.example.ports.input.user.register_usecase.RegisterResponse;
import com.example.ports.input.user.verify_account_usecase.VerifyAccountResponse;

public class UserMapperApplication {
    public static User toDomain(RegisterRequest registerRequest){
        return new User(registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getName(),
                registerRequest.getDateOfBirth());
    }

    public static RegisterResponse toResponse(User user){
        return new RegisterResponse(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getVerified(),
                user.getBlocked());
    }

    public static FindByUsernameResponse toResponseFindByUsername(User user){
        return new FindByUsernameResponse(user.getId(),
                user.getUsername(),
                user.getName(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getVerified(),
                user.getBlocked());
    }

    public static LoginResponse toResponseLogin(User user, String token){
        return new LoginResponse(token,
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getVerified(),
                user.getBlocked());
    }

    public static VerifyAccountResponse verifyAccountResponse(User user){
        return new VerifyAccountResponse(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getVerified(),
                user.getBlocked());
    }
    public static FindAllConsumersResponse toResponseFindAllUsers(User user){
        return new FindAllConsumersResponse(user.getId(),
                user.getUsername(),
                user.getName(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getVerified(),
                user.getBlocked());
    }

    public static BlockConsumerResponse toResponseBlock(User user){
        return new BlockConsumerResponse(user.getId(),
                user.getUsername(),
                user.getName(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getVerified(),
                user.getBlocked());
    }
}
