package com.example.ports.output.user;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface SendMailVerificationPort {
    void sendMail(Long id) throws MessagingException, UnsupportedEncodingException;
}
