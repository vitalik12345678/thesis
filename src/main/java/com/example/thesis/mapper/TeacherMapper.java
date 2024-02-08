package com.example.thesis.mapper;

import com.example.thesis.dto.CurrentTeacherDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.dto.TeacherRequestDTO;
import com.example.thesis.entity.Teacher;
import org.mapstruct.Mapping;

import java.util.List;

public interface TeacherMapper {

    Teacher fromTeacherRegistrationDTO(TeacherRegistrationDTO teacherRegistrationDTO);

    CurrentTeacherDTO toCurrentTeacherDTO (Teacher teacher);

    @Mapping(source = "user.firstName",target = "firstName")
    @Mapping(source = "user.lastName",target = "lastName")
    @Mapping(source = "user.role",target = "roleDTO")
    List<TeacherRequestDTO> toTeacherRequestDTOList (List<Teacher> teacherList);

    @Mapping(source = "user.firstName",target = "firstName")
    @Mapping(source = "user.lastName",target = "lastName")
    @Mapping(source = "user.role",target = "roleDTO")
    TeacherRequestDTO toTeacherRequestDTO(Teacher teacher);


}
