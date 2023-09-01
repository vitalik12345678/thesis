package com.example.thesis.factory;

import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.entity.User;
import com.example.thesis.mapper.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final EntityMapper entityMapper;

    public UserFactory (EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    public User fromRegistrationDTO (TeacherRegistrationDTO teacherRegistrationDTO) {
        return entityMapper.fromRegistrationDTO(teacherRegistrationDTO);
    }

}
