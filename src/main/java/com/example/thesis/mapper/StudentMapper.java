package com.example.thesis.mapper;

import com.example.thesis.dto.*;
import com.example.thesis.entity.Student;
import org.mapstruct.Mapping;

import java.util.List;

public interface StudentMapper {

    Student fromStudentRegistrationDTO(StudentRegistrationDTO studentRegistrationDTO);

    List<StudentFileInfoDTO> toStudentFileInfoDTOList (List<DocumentDTO> documentListByStudentId);

    CurrentStudentDTO toCurrentStudentDTO (Student student);

    @Mapping(source = "user.firstName",target = "firstName")
    @Mapping(source = "user.lastName",target = "lastName")
    List<StudentRequestDTO> toStudentRequestDTOList (List<Student> studentList);

    @Mapping(source = "user.firstName",target = "firstName")
    @Mapping(source = "user.lastName",target = "lastName")
    StudentRequestDTO toStudentRequestDTO(Student student);

    @Mapping(source = "adviser.user.firstName",target = "firstName")
    @Mapping(source = "adviser.user.lastName",target = "lastName")
    @Mapping(source = "adviser.teacherId",target = "teacherId")
    CurrentAdviserDTO toCurrentAdviserDTO (Student student);
}
