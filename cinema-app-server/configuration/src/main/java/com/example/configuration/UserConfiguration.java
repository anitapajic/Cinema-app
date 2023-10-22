package com.example.configuration;

import com.example.persistence.jwt.adapters.JWTServiceAdapter;
import com.example.persistence.user.adapters.*;
import com.example.persistence.user.repository.UserRepository;
import com.example.ports.input.user.block_consumer_usecase.BlockConsumerUsecase;
import com.example.ports.input.user.find_all_consumers_usecase.FindAllConsumersUsecase;
import com.example.ports.input.user.find_by_username_usecase.FindByUsernameUsecase;
import com.example.ports.input.user.login_usecase.LoginUsecase;
import com.example.ports.input.user.register_usecase.RegisterUsecase;
import com.example.ports.input.user.verify_account_usecase.VerifyAccountUsecase;
import com.example.ports.output.user.*;
import com.example.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;


@Configuration
public class UserConfiguration {

    //======================================================================================
    // PORTS
    //======================================================================================

    @Bean
    RegisterPort registerPort(UserRepository userRepository){
        return new RegisterAdapter(userRepository);
    }

    @Bean
    FindByUsernamePort findByUsernamePort(UserRepository userRepository){
        return new FindByUsernameAdapter(userRepository);
    }
    @Bean
    LoginPort loginPort(AuthenticationManager authenticationManager, JWTServiceAdapter jwtServiceAdapter){
        return new LoginAdapter(authenticationManager, jwtServiceAdapter);
    }
    @Bean
    VerifyAccountPort verifyAccountPort(UserRepository userRepository){
        return new VerifyAccountAdapter(userRepository);
    }
    @Bean
    SendMailVerificationPort sendMailVerificationPort(JavaMailSender javaMailSender){
        return new SendMailVerificationAdapter(javaMailSender);
    }
    @Bean
    FindAllConsumersPort findAllConsumersPort(UserRepository userRepository){
        return new FindAllConsumersAdapter(userRepository);
    }

    @Bean
    BlockConsumerPort blockConsumerPort(UserRepository userRepository){
        return new BlockConsumerAdapter(userRepository);
    }

    //======================================================================================
    // USE CASES
    //======================================================================================

    @Bean
    RegisterUsecase registerUsecase(RegisterPort registerPort, FindByUsernamePort findByUsernamePort, LoginPort loginPort, VerifyAccountPort verifyAccountPort, SendMailVerificationPort sendMailVerificationPort, FindAllConsumersPort findAllConsumersPort, BlockConsumerPort blockConsumerPort){
        return new UserService(registerPort, findByUsernamePort, loginPort, verifyAccountPort, sendMailVerificationPort, findAllConsumersPort, blockConsumerPort);
    }

    @Bean
    FindByUsernameUsecase findByUsernameUsecase(RegisterPort registerPort, FindByUsernamePort findByUsernamePort, LoginPort loginPort, VerifyAccountPort verifyAccountPort, SendMailVerificationPort sendMailVerificationPort, FindAllConsumersPort findAllConsumersPort, BlockConsumerPort blockConsumerPort){
        return new UserService(registerPort, findByUsernamePort, loginPort, verifyAccountPort, sendMailVerificationPort, findAllConsumersPort, blockConsumerPort);
    }

    @Bean
    LoginUsecase loginUsecase(RegisterPort registerPort, FindByUsernamePort findByUsernamePort, LoginPort loginPort, VerifyAccountPort verifyAccountPort, SendMailVerificationPort sendMailVerificationPort, FindAllConsumersPort findAllConsumersPort, BlockConsumerPort blockConsumerPort){
        return new UserService(registerPort, findByUsernamePort, loginPort, verifyAccountPort, sendMailVerificationPort, findAllConsumersPort, blockConsumerPort);
    }
    @Bean
    VerifyAccountUsecase verifyAccountUsecase(RegisterPort registerPort, FindByUsernamePort findByUsernamePort, LoginPort loginPort, VerifyAccountPort verifyAccountPort, SendMailVerificationPort sendMailVerificationPort, FindAllConsumersPort findAllConsumersPort, BlockConsumerPort blockConsumerPort){
        return new UserService(registerPort, findByUsernamePort, loginPort, verifyAccountPort, sendMailVerificationPort, findAllConsumersPort, blockConsumerPort);
    }
    @Bean
    FindAllConsumersUsecase findAllConsumersUsecase(RegisterPort registerPort, FindByUsernamePort findByUsernamePort, LoginPort loginPort, VerifyAccountPort verifyAccountPort, SendMailVerificationPort sendMailVerificationPort, FindAllConsumersPort findAllConsumersPort, BlockConsumerPort blockConsumerPort){
        return new UserService(registerPort, findByUsernamePort, loginPort, verifyAccountPort, sendMailVerificationPort, findAllConsumersPort, blockConsumerPort);
    }

    @Bean
    BlockConsumerUsecase blockConsumerUsecase(RegisterPort registerPort, FindByUsernamePort findByUsernamePort, LoginPort loginPort, VerifyAccountPort verifyAccountPort, SendMailVerificationPort sendMailVerificationPort, FindAllConsumersPort findAllConsumersPort, BlockConsumerPort blockConsumerPort){
        return new UserService(registerPort, findByUsernamePort, loginPort, verifyAccountPort, sendMailVerificationPort, findAllConsumersPort, blockConsumerPort);
    }


}
