package com.example.thesis.factory;

import com.example.thesis.dto.CurrentTeacherDTO;
import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherFactory {

    private final EntityMapper entityMapper;

    public Teacher fromTeacherRegistrationDTO(TeacherRegistrationDTO teacherRegistrationDTO) {
        return entityMapper.fromTeacherRegistrationDTO(teacherRegistrationDTO);
    }

    public CurrentTeacherDTO toCurrentTeacherDTO (Teacher teacher) {
        return entityMapper.toCurrentTeacherDTO(teacher);
    }
}
