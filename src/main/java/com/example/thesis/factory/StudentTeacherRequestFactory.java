package com.example.thesis.factory;

import com.example.thesis.dto.StudentTeacherRequestProfileDTO;
import com.example.thesis.entity.TeacherStudentRequest;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentTeacherRequestFactory {

    private final EntityMapper entityMapper;

    public StudentTeacherRequestProfileDTO toStudentTeacherRequestProfileDTO (TeacherStudentRequest request) {
        return entityMapper.toStudentTeacherRequestProfileDTO(request);
    }
}
