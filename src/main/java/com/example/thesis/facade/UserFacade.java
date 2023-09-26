package com.example.thesis.facade;

import com.example.thesis.dto.CurrentUserDTO;
import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.exception.NullObjectException;
import com.example.thesis.factory.UserFactory;
import com.example.thesis.security.UserPrincipal;
import com.example.thesis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    public CurrentUserDTO getCurrentUser () {
        var userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userPrincipal.getUser();
        if (Objects.isNull(user)) {
            throw new NullObjectException("User is null");
        }
        var currentUserDTO = userFactory.toCurrentUserDTO(user);

        switch (user.getRole().getName()) {
            case "teacher" -> {
                var currentTeacherDTO = teacherFacade.getCurrentTeacherDTOByUserId(user.getUserId());
                currentUserDTO.setTeacherDTO(currentTeacherDTO);
            }
            case "student" -> {
                var currentStudentDTO = studentFacade.getCurrentStudentDTOByUserId(user.getUserId());
                currentUserDTO.setStudentDTO(currentStudentDTO);
            }
        }

        return currentUserDTO;
    }
}
