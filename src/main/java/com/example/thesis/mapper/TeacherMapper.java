package com.example.thesis.mapper;

import com.example.thesis.dto.*;
import com.example.thesis.entity.Student;
import com.example.thesis.entity.Teacher;
import com.example.thesis.entity.TeacherStudentRequest;
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
