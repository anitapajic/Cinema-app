package com.example.service;

import com.example.exception.user.VerificationMailNotSent;
import com.example.mapper.user.UserMapperApplication;
import com.example.model.Token;
import com.example.model.User;
import com.example.ports.input.user.block_consumer_usecase.BlockConsumerResponse;
import com.example.ports.input.user.block_consumer_usecase.BlockConsumerUsecase;
import com.example.ports.input.user.find_all_consumers_usecase.FindAllConsumersResponse;
import com.example.ports.input.user.find_all_consumers_usecase.FindAllConsumersUsecase;
import com.example.ports.input.user.find_by_username_usecase.FindByUsernameResponse;
import com.example.ports.input.user.find_by_username_usecase.FindByUsernameUsecase;
import com.example.ports.input.user.login_usecase.LoginRequest;
import com.example.ports.input.user.login_usecase.LoginResponse;
import com.example.ports.input.user.login_usecase.LoginUsecase;
import com.example.ports.input.user.register_usecase.RegisterRequest;
import com.example.ports.input.user.register_usecase.RegisterResponse;
import com.example.ports.input.user.register_usecase.RegisterUsecase;
import com.example.ports.input.user.verify_account_usecase.VerifyAccountResponse;
import com.example.ports.input.user.verify_account_usecase.VerifyAccountUsecase;
import com.example.ports.output.user.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements RegisterUsecase,
                                    FindByUsernameUsecase,
                                    LoginUsecase,
                                    VerifyAccountUsecase,
                                    FindAllConsumersUsecase,
                                    BlockConsumerUsecase {
    private final RegisterPort registerPort;
    private final FindByUsernamePort findByUsernamePort;
    private final LoginPort loginPort;
    private final VerifyAccountPort verifyAccountPort;
    private final SendMailVerificationPort sendMailVerificationPort;
    private final FindAllConsumersPort findAllConsumersPort;
    private final BlockConsumerPort blockConsumerPort;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest){
        User user = UserMapperApplication.toDomain(registerRequest);
        User savedUser = registerPort.register(user);
        try{
            sendMailVerificationPort.sendMail(savedUser.getId());
        }catch(Exception e){
            throw new RuntimeException(new VerificationMailNotSent(savedUser.getId()));
        }
        return UserMapperApplication.toResponse(savedUser);
    }

    @Override
    public FindByUsernameResponse findByUsername(String username){
        User user = findByUsernamePort.findByUsername(username);
        return UserMapperApplication.toResponseFindByUsername(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest){
        User user = findByUsernamePort.findByUsername(loginRequest.getUsername());
        Token token = loginPort.login(user);
        return UserMapperApplication.toResponseLogin(user, token.getToken());
    }

    @Override
    public VerifyAccountResponse verify(Long id){
        User user = verifyAccountPort.verify(id);
        return UserMapperApplication.verifyAccountResponse(user);
    }

    @Override
    public List<FindAllConsumersResponse> findAllUsers(){
        return findAllConsumersPort.findAllUsers().stream()
                .map(UserMapperApplication::toResponseFindAllUsers)
                .collect(Collectors.toList());
    }

    @Override
    public BlockConsumerResponse block(Long id){
        User user = blockConsumerPort.block(id);
        return UserMapperApplication.toResponseBlock(user);
    }

}
