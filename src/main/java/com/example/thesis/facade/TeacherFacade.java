package com.example.thesis.facade;

import com.example.thesis.dto.CurrentTeacherDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.entity.User;
import com.example.thesis.factory.TeacherFactory;
import com.example.thesis.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TeacherFacade {

    private final TeacherService teacherService;
    private final TeacherFactory teacherFactory;

    public void create(TeacherRegistrationDTO teacherRegistrationDTO, User user) {
        var teacher = teacherFactory.fromTeacherRegistrationDTO(teacherRegistrationDTO);
        teacher.setUser(user);
        teacherService.save(teacher);
    }

    @Transactional(readOnly = true)
    public CurrentTeacherDTO getCurrentTeacherDTOByUserId (Long userId) {
        var teacher = teacherService.findByUserId(userId);
        return teacherFactory.toCurrentTeacherDTO(teacher);
    }
}
