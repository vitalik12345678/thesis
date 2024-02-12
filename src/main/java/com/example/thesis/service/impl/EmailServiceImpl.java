package com.example.thesis.service.impl;

import com.example.thesis.entity.EmailDetails;
import com.example.thesis.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;

    @Override
    public void sendInvitation(List<EmailDetails> emailDetails) {
        emailDetails.forEach( detail -> {
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = detail.text();
            try {
                helper.setTo(detail.to());
                helper.setFrom(detail.from());
                helper.setSubject(detail.subject());
                helper.setText(htmlMsg, true);
                sender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
