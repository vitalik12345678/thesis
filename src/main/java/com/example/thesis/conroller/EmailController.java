package com.example.thesis.conroller;

import com.example.thesis.facade.EmailFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailFacade emailFacade;

    @PostMapping(value = "/csv")
    @PreAuthorize("hasAnyAuthority('HoD')")
    public ResponseEntity<Void> sendInvitationByFile(@RequestParam(name = "file") MultipartFile file) {
        return emailFacade.sendInvitation(file);
    }


}
