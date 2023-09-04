package com.example.thesis.facade;

import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.factory.UserFactory;
import com.example.thesis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserFactory userFactory;
    private final RoleFacade roleFacade;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;

    @Transactional
    public void saveTeacher (TeacherRegistrationDTO teacherRegistrationDTO) {
        var user = userFactory.fromRegistrationDTO(teacherRegistrationDTO);
        user = userService.saveTeacher(user);
        teacherFacade.create(teacherRegistrationDTO, user);
    }

    @Transactional
    public void saveStudent (StudentRegistrationDTO studentRegistrationDTO) {
        var user = userFactory.fromRegistrationDTO(studentRegistrationDTO);
        user = userService.saveStudent(user);
        studentFacade.create(studentRegistrationDTO,user);
    }
}
