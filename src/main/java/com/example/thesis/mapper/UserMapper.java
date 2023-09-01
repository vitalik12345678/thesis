package com.example.thesis.mapper;

import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.entity.User;

public interface UserMapper {

    User fromRegistrationDTO(TeacherRegistrationDTO teacherRegistrationDTO);

}
