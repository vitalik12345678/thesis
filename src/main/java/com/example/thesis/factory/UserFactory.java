package com.example.thesis.factory;

import com.example.thesis.dto.CurrentUserDTO;
import com.example.thesis.dto.RoleDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.dto.UserDTO;
import com.example.thesis.entity.Role;
import com.example.thesis.entity.User;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final EntityMapper entityMapper;
    private final RoleFactory roleFactory;

    public User fromRegistrationDTO (TeacherRegistrationDTO teacherRegistrationDTO) {
        return entityMapper.fromRegistrationDTO(teacherRegistrationDTO);
    }

    public Role toRole (RoleDTO roleDTO) {
        return roleFactory.toRole(roleDTO);
    }

    public CurrentUserDTO toCurrentUserDTO (User user) {
        return entityMapper.toCurrentUserDTO(user);
    }

    public UserDTO toUserDTO(User id) {
        return entityMapper.toUserDTO(id);
    }

    public List<UserDTO> toUserDTOList(List<User> all) {
        return entityMapper.toUserDTOList(all);
    }
}
