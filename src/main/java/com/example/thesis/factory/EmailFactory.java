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

    public List<EmailDetails> toEmailDetailsList(List<EmailCsvCreationDTO> dtos) {
        return dtos.stream().map(this::toEmailDetails).toList();
    }

    public EmailDetails toEmailDetails(EmailCsvCreationDTO dtos) {
        return new EmailDetails(mail,dtos.getEmail(),"Notification about account creation" ,( dtos.getRole().equals("student") ? " it is your token %s and url testStudnet".formatted(dtos.getToken())
                :
                "  it is your token %s and url testTeacher".formatted(dtos.getToken())));
    }

}
