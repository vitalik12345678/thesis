package com.example.thesis.mapper;

import com.example.thesis.dto.FullUserInfoDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.dto.UserDTO;
import com.example.thesis.entity.User;
import org.mapstruct.Mapping;

import java.util.List;

public interface UserMapper {

    User fromRegistrationDTO(TeacherRegistrationDTO teacherRegistrationDTO);

    @Mapping(source = "role",target = "roleDTO")
    FullUserInfoDTO toFullUserInfoDTO(User user);

    UserDTO toUserDTO(User id);

    List<UserDTO> toUserDTOList(List<User> all);
}
