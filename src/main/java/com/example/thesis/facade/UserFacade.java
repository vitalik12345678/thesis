package com.example.thesis.facade;

import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.factory.UserFactory;
import com.example.thesis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserFactory userFactory;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;

    public void saveTeacher (TeacherRegistrationDTO teacherRegistrationDTO) {
        var user = userService.save(userFactory.fromRegistrationDTO(teacherRegistrationDTO));
        teacherFacade.create(teacherRegistrationDTO, user);
    }

    public void saveStudent (StudentRegistrationDTO studentRegistrationDTO) {
        var user = userService.save(userFactory.fromRegistrationDTO(studentRegistrationDTO));
        studentFacade.create(studentRegistrationDTO,user);
    }
}
