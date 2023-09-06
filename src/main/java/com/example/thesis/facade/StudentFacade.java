package com.example.thesis.facade;

import com.example.thesis.dto.StudentFileInfoDTO;
import com.example.thesis.dto.StudentRegistrationDTO;
import com.example.thesis.entity.User;
import com.example.thesis.factory.StudentFactory;
import com.example.thesis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentFacade {

    private final StudentFactory studentFactory;
    private final StudentService studentService;
    private final DocumentFacade documentFacade;

    public void create (StudentRegistrationDTO studentRegistrationDTO, User user) {
        var student = studentFactory.fromStudentRegistrationDTO(studentRegistrationDTO);
        student.setUser(user);
        studentService.save(student);
    }

    public List<StudentFileInfoDTO> findFileInfoListById (Long studentId) {
        return studentFactory.toStudentFileInfoDTOList(documentFacade.findDocumentListByStudentId(studentId));
    }
}
