package com.example.thesis.facade;

import com.example.thesis.dto.JwtDTO;
import com.example.thesis.dto.LoginDTO;
import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final AuthenticationService authenticationService;
    private final UserFacade userFacade;

    @Transactional
    public JwtDTO authorize (LoginDTO loginDTO) {

        return authenticationService.authorize(loginDTO);

    }

    @Transactional
    public void registration (StudentRegistrationDTO studentRegistrationDTO) {
        userFacade.saveStudent(studentRegistrationDTO);
    }

    @Transactional
    public void registration (TeacherRegistrationDTO teacherRegistrationDTO) {
        userFacade.saveTeacher(teacherRegistrationDTO);
    }
}
