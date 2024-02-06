package com.example.thesis.mapper;

import com.example.thesis.dto.CurrentUserDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.entity.User;
import org.mapstruct.Mapping;

public interface UserMapper {

    User fromRegistrationDTO(TeacherRegistrationDTO teacherRegistrationDTO);

    @Mapping(source = "role",target = "roleDTO")
    CurrentUserDTO toCurrentUserDTO (User user);
}
