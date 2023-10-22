package com.example.persistence.user.adapters;

import com.example.ports.output.user.SendMailVerificationPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class SendMailVerificationAdapter implements SendMailVerificationPort {
    private final JavaMailSender mailSender;

    public void sendMail(Long id) throws MessagingException, UnsupportedEncodingException {
        String subject = "Please verify your account";
        String senderName = "Cinema App";

        String mailContent = "<p>Dear, user </p>";
        mailContent +="<p>Please click the link below to verify your account:</p>";
        mailContent +="<h3><a href=\"" + "http://localhost:8085/api/user/verify/" + id + "\">VERIFY</a></h3>";
        mailContent +="<p>Thank you!<br>Cinema App Team</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("UberAppTim19@gmail.com", senderName);
        helper.setTo("anitaapajic@gmail.com");
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message);
    }
}
