package com.example.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class VerificationMailNotSent extends RuntimeException{
    public VerificationMailNotSent(Long id) {
        super(String.format("Verification mail with user id: %s is not sent!", id));
    }
}
