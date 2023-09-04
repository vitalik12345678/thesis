package com.example.thesis.factory;

import com.example.thesis.dto.RoleDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.entity.Role;
import com.example.thesis.entity.User;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
