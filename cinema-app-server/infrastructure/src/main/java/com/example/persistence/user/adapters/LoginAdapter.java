package com.example.persistence.user.adapters;

import com.example.model.Token;
import com.example.model.User;
import com.example.persistence.jwt.adapters.JWTServiceAdapter;
import com.example.ports.output.user.LoginPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginAdapter implements LoginPort {
    private final AuthenticationManager authenticationManager;
    private final JWTServiceAdapter jwtServiceAdapter;

    @Override
    public Token login(User user){
        if(!user.getVerified() || user.getBlocked()) return null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        }catch (Exception e){
            System.out.println(e);
        }
        String token = jwtServiceAdapter.generateToken(user);
        return new Token(token, user.getId(), user.getRole().toString());
    }
}
