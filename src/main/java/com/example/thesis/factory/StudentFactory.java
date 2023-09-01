package com.example.thesis.factory;

import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentFactory {

    private final EntityMapper entityMapper;

    public Student fromStudentRegistrationDTO(StudentRegistrationDTO registrationDTO) {
        return entityMapper.fromStudentRegistrationDTO(registrationDTO);
    }
}
