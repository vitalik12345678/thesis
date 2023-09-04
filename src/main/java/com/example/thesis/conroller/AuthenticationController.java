package com.example.thesis.conroller;

import com.example.thesis.dto.JwtDTO;
import com.example.thesis.dto.LoginDTO;
import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.facade.AuthenticationFacade;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationController {

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public AuthenticationController (AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping("student/signup")
    public ResponseEntity<Void> studentSignUp(@RequestBody @Valid StudentRegistrationDTO studentRegistrationDTO) {

        authenticationFacade.registration(studentRegistrationDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("teacher/signup")
    public ResponseEntity<Void> teacherSighUp(@RequestBody @Valid TeacherRegistrationDTO teacherRegistrationDTO) {

        authenticationFacade.registration(teacherRegistrationDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@RequestBody LoginDTO loginDTO) {
        log.trace("authentication for user '{}'", loginDTO.getEmail());

        JwtDTO jwtDTO = authenticationFacade.authorize(loginDTO);

        return ResponseEntity.status(HttpStatus.OK).body(jwtDTO);
    }


}
