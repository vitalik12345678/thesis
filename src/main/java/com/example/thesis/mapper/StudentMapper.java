package com.example.thesis.mapper;

import com.example.thesis.dto.CurrentStudentDTO;
import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.dto.StudentFileInfoDTO;
import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.entity.Student;

import java.util.List;

public interface StudentMapper {

    Student fromStudentRegistrationDTO(StudentRegistrationDTO studentRegistrationDTO);

    List<StudentFileInfoDTO> toStudentFileInfoDTOList (List<DocumentDTO> documentListByStudentId);

    CurrentStudentDTO toCurrentStudentDTO (Student student);
}
