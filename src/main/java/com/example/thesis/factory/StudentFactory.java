package com.example.thesis.factory;

import com.example.thesis.dto.CurrentStudentDTO;
import com.example.thesis.dto.DocumentDTO;
import com.example.thesis.dto.StudentFileInfoDTO;
import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.entity.Student;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentFactory {

    private final EntityMapper entityMapper;

    public Student fromStudentRegistrationDTO(StudentRegistrationDTO registrationDTO) {
        return entityMapper.fromStudentRegistrationDTO(registrationDTO);
    }

    public List<StudentFileInfoDTO> toStudentFileInfoDTOList (List<DocumentDTO> documentListByStudentId) {
        return entityMapper.toStudentFileInfoDTOList(documentListByStudentId);
    }

    public CurrentStudentDTO toCurrentStudentDTO (Student student) {
        return entityMapper.toCurrentStudentDTO(student);
    }
}
