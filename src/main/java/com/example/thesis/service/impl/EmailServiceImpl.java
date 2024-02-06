package com.example.thesis.service.impl;

import com.example.thesis.entity.EmailDetails;
import com.example.thesis.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;

    @Override
    public void sendInvitation(List<EmailDetails> emailDetails) {
        emailDetails.forEach( detail -> {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setText(detail.text());
            mailMessage.setTo(detail.to());
            mailMessage.setFrom(detail.from());
            mailMessage.setSubject(detail.text());
            sender.send(mailMessage);
        });
    }
}
