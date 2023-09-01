package com.example.thesis.mapper;

import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.entity.Student;

public interface StudentMapper {

    Student fromStudentRegistrationDTO(StudentRegistrationDTO studentRegistrationDTO);

}
