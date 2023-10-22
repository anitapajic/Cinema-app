package com.example.web.controller.user;

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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final RegisterUsecase registerUsecase;
    private final FindByUsernameUsecase findByUsernameUsecase;
    private final LoginUsecase loginUsecase;
    private final VerifyAccountUsecase verifyAccountUsecase;
    private final FindAllConsumersUsecase findAllConsumersUsecase;
    private final BlockConsumerUsecase blockConsumerUsecase;
    @PostMapping(value = "/register")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity registration(@Valid @RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = registerUsecase.register(registerRequest);
        return new ResponseEntity(registerResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity findByUsername(@Valid @PathVariable String username){
        FindByUsernameResponse findByUsernameResponse = findByUsernameUsecase.findByUsername(username);
        return new ResponseEntity(findByUsernameResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = loginUsecase.login(loginRequest);
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/verify/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CONSUMER')")
    public ResponseEntity verify(@Valid @PathVariable Long id){
        VerifyAccountResponse verifyAccountResponse = verifyAccountUsecase.verify(id);
        return new ResponseEntity(verifyAccountResponse, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity getAllUsers(){
        List<FindAllConsumersResponse> findAllConsumersResponse = findAllConsumersUsecase.findAllUsers();
        return new ResponseEntity<>(findAllConsumersResponse, HttpStatus.OK);
    }
    @GetMapping(value = "/block/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity block(@Valid @PathVariable Long id){
        BlockConsumerResponse blockConsumerResponse = blockConsumerUsecase.block(id);
        return new ResponseEntity(blockConsumerResponse, HttpStatus.OK);
    }

}
