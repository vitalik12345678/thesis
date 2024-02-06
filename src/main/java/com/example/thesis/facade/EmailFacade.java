package com.example.thesis.facade;

import com.example.thesis.dto.EmailCsvCreationDTO;
import com.example.thesis.factory.EmailFactory;
import com.example.thesis.service.EmailService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmailFacade {

    private final EmailFactory emailFactory;
    private final EmailService emailService;
    private final TokenFacade tokenFacade;

    @Transactional
    public ResponseEntity<Void> sendInvitation(MultipartFile file) {

        try (Reader reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes()))) {

            CsvToBean<EmailCsvCreationDTO> mapper = new CsvToBeanBuilder<EmailCsvCreationDTO>(reader)
                    .withType(EmailCsvCreationDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            var list = mapper.parse();
            list.forEach(item -> item.setToken(UUID.randomUUID().toString()));

            var tokens = tokenFacade.createAll(list);
            var emailDetails = emailFactory.toEmailDetailsList(list);

            emailService.sendInvitation(emailDetails);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }
}
