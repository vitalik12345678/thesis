package com.example.thesis.mapper;

import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.entity.Teacher;

public interface TeacherMapper {

    Teacher fromTeacherRegistrationDTO(TeacherRegistrationDTO teacherRegistrationDTO);

}