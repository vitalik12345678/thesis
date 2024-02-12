package com.example.thesis.factory;

import com.example.thesis.dto.EmailCsvCreationDTO;
import com.example.thesis.entity.EmailDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailFactory {

    @Value("${spring.mail.username}")
    private String mail;

    @Value("${front.signUp.teacher}")
    private String teacherSignUpURL;

    @Value("${front.signUp.student}")
    private String studentSignUpURL;

    public List<EmailDetails> toEmailDetailsList(List<EmailCsvCreationDTO> dtos) {
        return dtos.stream().map(this::toEmailDetails).toList();
    }

    public EmailDetails toEmailDetails(EmailCsvCreationDTO dtos) {
        var isTeacher = dtos.getRole().equals("teacher");
        return new EmailDetails(
                mail,
                dtos.getEmail(),
                "Thesis Tracker Invitation",
                "<p><strong>You have been invited to join Thesis Tracker:</strong><br/><a href=\"%s\">Click to sign up.</a></p>"
                        .formatted((isTeacher ? teacherSignUpURL : studentSignUpURL).concat("?token=%s".formatted(dtos.getToken())))
        );
    }

}
