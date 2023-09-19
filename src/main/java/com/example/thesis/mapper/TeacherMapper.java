package com.example.thesis.mapper;

import com.example.thesis.dto.CurrentTeacherDTO;
import com.example.thesis.dto.StudentRequestDTO;
import com.example.thesis.dto.TeacherRegistrationDTO;
import com.example.thesis.dto.TeacherRequestDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import org.mapstruct.Mapping;

import java.util.List;

public interface TeacherMapper {

    Teacher fromTeacherRegistrationDTO(TeacherRegistrationDTO teacherRegistrationDTO);

    CurrentTeacherDTO toCurrentTeacherDTO (Teacher teacher);

    @Mapping(source = "user.firstName",target = "firstName")
    @Mapping(source = "user.lastName",target = "lastName")
    List<TeacherRequestDTO> toTeacherRequestDTOList (List<Teacher> teacherList);

    @Mapping(source = "user.firstName",target = "firstName")
    @Mapping(source = "user.lastName",target = "lastName")
    TeacherRequestDTO toTeacherRequestDTO(Teacher teacher);
}
